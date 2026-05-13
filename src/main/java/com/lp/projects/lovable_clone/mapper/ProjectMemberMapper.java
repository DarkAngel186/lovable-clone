package com.lp.projects.lovable_clone.mapper;

import com.lp.projects.lovable_clone.dto.member.ProjectMemberResponse;
import com.lp.projects.lovable_clone.entity.ProjectMember;
import com.lp.projects.lovable_clone.entity.User;
import com.lp.projects.lovable_clone.enums.ProjectRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "projectRole", constant = "OWNER")
    ProjectMemberResponse userToProjectMemberResponse(User user);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.updatedAt", target = "updatedAt")
    ProjectMemberResponse projectMemberToProjectMemberResponse(ProjectMember projectMember);

    List<ProjectMemberResponse> listProjectMemberToListProjectMemberResponse(List<ProjectMember> projectMembers);
}
