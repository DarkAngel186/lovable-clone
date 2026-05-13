package com.lp.projects.lovable_clone.service;

import com.lp.projects.lovable_clone.dto.project.ProjectRequest;
import com.lp.projects.lovable_clone.dto.project.ProjectResponse;
import com.lp.projects.lovable_clone.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {

    List<ProjectSummaryResponse> getUserProjects(Long userId);

    ProjectResponse getUserProjectById(Long id, Long userId);

    ProjectResponse createProject(ProjectRequest request, Long userId);

    ProjectResponse updateProject(Long id, ProjectRequest request, Long userId);

    void softDeleteProject(Long id, Long userId);
}
