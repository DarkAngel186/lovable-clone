package com.lp.projects.lovable_clone.controller;

import com.lp.projects.lovable_clone.dto.subscription.*;
import com.lp.projects.lovable_clone.entity.Subscription;
import com.lp.projects.lovable_clone.service.PlanService;
import com.lp.projects.lovable_clone.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BillingController {

    PlanService planService;

    SubscriptionService subscriptionService;

    @GetMapping("/plans")
    public ResponseEntity<List<PlanResponse>> getAllActivePlans() {
        return ResponseEntity.ok(planService.getAllActivePlans());
    }

    @GetMapping("/me/subscriptions")
    public ResponseEntity<List<SubscriptionResponse>> getMySubscriptions() {
        Long userId = 1L;
        return ResponseEntity.ok(subscriptionService.getMySubscriptions(userId));
    }

    @PostMapping("/stripe/checkout")
    public ResponseEntity<CheckoutResponse> createCheckoutRepsonse(
            @RequestBody CheckoutRequest request
    ) {
        Long userId = 1L;
        return ResponseEntity.ok(subscriptionService.createCheckoutSession(request, userId));
    }

    @PostMapping("/stripe/portal")
    public ResponseEntity<PortalResponse> openCustomerPortal() {
        Long userId = 1L;
        return ResponseEntity.ok(subscriptionService.openCustomerPortal(userId));
    }
}
