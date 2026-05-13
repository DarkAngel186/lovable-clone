package com.lp.projects.lovable_clone.service.impl;

import com.lp.projects.lovable_clone.dto.subscription.PlanResponse;
import com.lp.projects.lovable_clone.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanSercviceImpl implements PlanService {

    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
