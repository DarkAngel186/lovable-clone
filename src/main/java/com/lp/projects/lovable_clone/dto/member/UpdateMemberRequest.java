package com.lp.projects.lovable_clone.dto.member;

import com.lp.projects.lovable_clone.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRequest(
        @NotNull ProjectRole role
) {}
