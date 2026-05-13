package com.lp.projects.lovable_clone.mapper;

import com.lp.projects.lovable_clone.dto.project.ProjectResponse;
import com.lp.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.lp.projects.lovable_clone.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse projectToProjectResponse(Project project);

    ProjectSummaryResponse projectToProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> listProjectToListProjectSummaryResponse(List<Project> project);
}
