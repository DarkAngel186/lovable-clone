package com.lp.projects.lovable_clone.service;

import com.lp.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.lp.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.lp.projects.lovable_clone.dto.subscription.PortalResponse;
import com.lp.projects.lovable_clone.dto.subscription.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionResponse> getMySubscriptions(Long userId);

    CheckoutResponse createCheckoutSession(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
