package com.lp.projects.lovable_clone.service.impl;

import com.lp.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.lp.projects.lovable_clone.dto.member.ProjectMemberResponse;
import com.lp.projects.lovable_clone.dto.member.UpdateMemberRequest;
import com.lp.projects.lovable_clone.entity.Project;
import com.lp.projects.lovable_clone.entity.ProjectMember;
import com.lp.projects.lovable_clone.entity.ProjectMemberId;
import com.lp.projects.lovable_clone.entity.User;
import com.lp.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.lp.projects.lovable_clone.repository.ProjectMemberRepository;
import com.lp.projects.lovable_clone.repository.ProjectRepository;
import com.lp.projects.lovable_clone.repository.UserRepository;
import com.lp.projects.lovable_clone.security.AuthUtil;
import com.lp.projects.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMemberMapper projectMemberMapper;
    AuthUtil authUtil;

    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<ProjectMemberResponse> getProjectMembers(Long projectId) {

         return projectMemberRepository
                 .findByIdProjectId(projectId)
                 .stream()
                 .map(projectMemberMapper::projectMemberToProjectMemberResponse)
                 .toList();
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request) {

        Long userId = getUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        User invitee = userRepository
                .findByUsername(request.username())
                .orElseThrow();

        if(invitee.getId().equals(userId))
            throw new RuntimeException("You are not allowed to invite yourself!!");

        ProjectMemberId id = new ProjectMemberId(projectId, invitee.getId());

        if(projectMemberRepository.existsById(id))
            throw new RuntimeException("You are not allowed to invite again!!");

        ProjectMember projectMember = ProjectMember
                .builder()
                .id(id)
                .project(project)
                .projectRole(request.role())
                .user(invitee)
                .invitedAt(Instant.now())
                .build();

        projectMember = projectMemberRepository.save(projectMember);

        return projectMemberMapper.projectMemberToProjectMemberResponse(projectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public ProjectMemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRequest request) {

        Project project = getAccessibleProjectById(projectId, getUserId());

        ProjectMemberId id = new ProjectMemberId(projectId, memberId);

        ProjectMember projectMember = projectMemberRepository
                .findById(id)
                .orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.projectMemberToProjectMemberResponse(projectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void deleteProjectMember(Long projectId, Long memberId) {

        Project project = getAccessibleProjectById(projectId, getUserId());

        ProjectMemberId id = new ProjectMemberId(projectId, memberId);

        if(!projectMemberRepository.existsById(id))
            throw new RuntimeException("Member doesn't exist!!");

        projectMemberRepository.deleteById(id);
    }

    /// INTERNAL METHODS:
    private Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository
                .findAccessibleProjectById(id, userId)
                .orElseThrow();
    }

    private Long getUserId() {
        return authUtil.getCurrentUserId();
    }
}
