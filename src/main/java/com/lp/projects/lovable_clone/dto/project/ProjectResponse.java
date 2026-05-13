package com.lp.projects.lovable_clone.dto.project;

import com.lp.projects.lovable_clone.dto.auth.UserProfleResponse;

import java.time.Instant;

public record ProjectResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt,
        UserProfleResponse owner
) {}
