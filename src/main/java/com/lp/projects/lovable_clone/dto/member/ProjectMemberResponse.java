package com.lp.projects.lovable_clone.dto.member;

import com.lp.projects.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record ProjectMemberResponse(
        Long id,
        String username,
        String name,
        ProjectRole projectRole,
        Instant updatedAt
) {}
