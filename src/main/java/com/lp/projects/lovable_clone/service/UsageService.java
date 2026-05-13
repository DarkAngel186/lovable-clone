package com.lp.projects.lovable_clone.service;

import com.lp.projects.lovable_clone.dto.usage.PlanLimitResponse;
import com.lp.projects.lovable_clone.dto.usage.UsageTodayResponse;

public interface UsageService {

    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
