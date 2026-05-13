package com.lp.projects.lovable_clone.dto.auth;

public record AuthResponse(
        String token,
        UserProfleResponse user
) {}
