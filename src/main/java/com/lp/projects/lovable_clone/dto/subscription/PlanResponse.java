package com.lp.projects.lovable_clone.dto.subscription;

import jakarta.persistence.Column;

public record PlanResponse(
        Long id,
        String name,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Integer maxPreviews,
        Boolean unlimitedAi
) {}
