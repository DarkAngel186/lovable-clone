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
import com.lp.projects.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @Override
    public List<ProjectMemberResponse> getProjectMembers(Long projectId, Long userId) {

        List<ProjectMemberResponse> memberResponses = new ArrayList<>();

        Project project = getAccessibleProjectById(projectId, userId);
        memberResponses.add(projectMemberMapper.userToProjectMemberResponse(project.getOwner()));

         memberResponses.addAll(projectMemberRepository
                 .findByIdProjectId(projectId)
                 .stream()
                 .map(projectMemberMapper::projectMemberToProjectMemberResponse)
                 .toList());

        return memberResponses;
    }

    @Override
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {

        Project project = getAccessibleProjectById(projectId, userId);

        if(!project.getOwner().getId().equals(userId))
            throw new RuntimeException("You are not allowed to add member in this project!!");

        User invitee = userRepository
                .findByEmail(request.email())
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
    public ProjectMemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRequest request, Long userId) {

        Project project = getAccessibleProjectById(projectId, userId);

        if(!project.getOwner().getId().equals(userId))
            throw new RuntimeException("Not allowed!!");

        ProjectMemberId id = new ProjectMemberId(projectId, memberId);

        ProjectMember projectMember = projectMemberRepository
                .findById(id)
                .orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.projectMemberToProjectMemberResponse(projectMember);
    }

    @Override
    public void deleteProjectMember(Long projectId, Long memberId, Long userId) {

        Project project = getAccessibleProjectById(projectId, userId);

        if(!project.getOwner().getId().equals(userId))
            throw new RuntimeException("You are not allowed to add member in this project!!");

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
}
