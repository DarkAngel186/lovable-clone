package com.lp.projects.lovable_clone.service.impl;

import com.lp.projects.lovable_clone.dto.usage.PlanLimitResponse;
import com.lp.projects.lovable_clone.dto.usage.UsageTodayResponse;
import com.lp.projects.lovable_clone.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {

    @Override
    public UsageTodayResponse getTodayUsage(Long userId) {
        return null;
    }

    @Override
    public PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
