package com.lp.projects.lovable_clone.service.impl;

import com.lp.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.lp.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.lp.projects.lovable_clone.dto.subscription.PortalResponse;
import com.lp.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import com.lp.projects.lovable_clone.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public List<SubscriptionResponse> getMySubscriptions(Long userId) {
        return List.of();
    }

    @Override
    public CheckoutResponse createCheckoutSession(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
