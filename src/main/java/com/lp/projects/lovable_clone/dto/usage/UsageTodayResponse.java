package com.lp.projects.lovable_clone.dto.usage;

public record UsageTodayResponse(
        Integer tokensUsed,
        Integer tokensLimit,
        Integer previewsRunning,
        Integer previewsLimit
) {}
