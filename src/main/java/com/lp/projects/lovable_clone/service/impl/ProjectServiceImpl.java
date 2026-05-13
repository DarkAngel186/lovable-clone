package com.lp.projects.lovable_clone.service.impl;

import com.lp.projects.lovable_clone.dto.project.ProjectRequest;
import com.lp.projects.lovable_clone.dto.project.ProjectResponse;
import com.lp.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.lp.projects.lovable_clone.entity.Project;
import com.lp.projects.lovable_clone.entity.User;
import com.lp.projects.lovable_clone.error.ResourceNotFoundException;
import com.lp.projects.lovable_clone.mapper.ProjectMapper;
import com.lp.projects.lovable_clone.repository.ProjectRepository;
import com.lp.projects.lovable_clone.repository.UserRepository;
import com.lp.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;

    UserRepository userRepository;

    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {

        User user = userRepository.findById(userId).orElseThrow();

        Project project = Project
                .builder()
                .name(request.name())
                .owner(user)
                .isPublic(Boolean.FALSE)
                .build();

        project = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

        /*
        return projectRepository
                .findAllAccessibleProjects(userId)
                .stream()
                .map(projectMapper::projectToProjectSummaryResponse)
                .collect(Collectors.toList());
        */

        return projectMapper
                .listProjectToListProjectSummaryResponse(projectRepository
                                .findAllAccessibleProjects(userId));
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        return projectMapper.projectToProjectResponse(getAccessibleProjectById(id, userId));
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {

        Project project = getAccessibleProjectById(id, userId);

        if(!project.getOwner().getId().equals(userId))
            throw new RuntimeException("You are not allowed to update this project!!");

        project.setName(request.name());
        project = projectRepository.save(project);

        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    public void softDeleteProject(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);

        if(!project.getOwner().getId().equals(userId))
            throw new RuntimeException("You are not allowed to delete this project!!");

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }


    /// INTERNAL METHODS:
    private Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository
                .findAccessibleProjectById(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", id.toString()));
    }
}
