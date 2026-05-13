package com.lp.projects.lovable_clone.service;

import com.lp.projects.lovable_clone.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {

    List<PlanResponse> getAllActivePlans();
}
