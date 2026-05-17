package com.lp.projects.lovable_clone.service.impl;

import com.lp.projects.lovable_clone.dto.project.ProjectRequest;
import com.lp.projects.lovable_clone.dto.project.ProjectResponse;
import com.lp.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.lp.projects.lovable_clone.entity.Project;
import com.lp.projects.lovable_clone.entity.ProjectMember;
import com.lp.projects.lovable_clone.entity.ProjectMemberId;
import com.lp.projects.lovable_clone.entity.User;
import com.lp.projects.lovable_clone.enums.ProjectRole;
import com.lp.projects.lovable_clone.error.ResourceNotFoundException;
import com.lp.projects.lovable_clone.mapper.ProjectMapper;
import com.lp.projects.lovable_clone.repository.ProjectMemberRepository;
import com.lp.projects.lovable_clone.repository.ProjectRepository;
import com.lp.projects.lovable_clone.repository.UserRepository;
import com.lp.projects.lovable_clone.security.AuthUtil;
import com.lp.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {

        Long userId = getUserId();
        User user = userRepository.getReferenceById(userId);

        Project project = Project
                .builder()
                .name(request.name())
                .isPublic(Boolean.FALSE)
                .build();

        project = projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), user.getId());
        ProjectMember projectMember = ProjectMember
                .builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .project(project)
                .user(user)
                .invitedAt(Instant.now())
                .acceptedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);

        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {

        return projectMapper
                .listProjectToListProjectSummaryResponse(projectRepository
                                .findAllAccessibleProjects(getUserId()));
    }

    @Override
    @PreAuthorize("@security.canViewProject(#id)")
    public ProjectResponse getUserProjectById(Long id) {
        return projectMapper.projectToProjectResponse(getAccessibleProjectById(id, getUserId()));
    }

    @Override
    @PreAuthorize("@security.canEditProject(#id)")
    public ProjectResponse updateProject(Long id, ProjectRequest request) {

        Project project = getAccessibleProjectById(id, getUserId());

        project.setName(request.name());
        project = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#id)")
    public void softDeleteProject(Long id) {
        Project project = getAccessibleProjectById(id, getUserId());

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }


    /// INTERNAL METHODS:
    private Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository
                .findAccessibleProjectById(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", id.toString()));
    }

    private Long getUserId() {
        return authUtil.getCurrentUserId();
    }
}
