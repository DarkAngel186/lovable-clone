package com.lp.projects.lovable_clone.dto.auth;

public record UserProfleResponse(
        Long id,
        String email,
        String name,
        String avtarUrl
) {}
