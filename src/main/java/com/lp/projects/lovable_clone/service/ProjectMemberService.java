package com.lp.projects.lovable_clone.service;

import com.lp.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.lp.projects.lovable_clone.dto.member.ProjectMemberResponse;
import com.lp.projects.lovable_clone.dto.member.UpdateMemberRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProjectMemberService {

    List<ProjectMemberResponse> getProjectMembers(Long projectId);

    ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    ProjectMemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRequest request);

    void deleteProjectMember(Long projectId, Long memberId);
}
