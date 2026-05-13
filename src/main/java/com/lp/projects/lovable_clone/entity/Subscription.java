package com.lp.projects.lovable_clone.entity;

import com.lp.projects.lovable_clone.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

//@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    User user;

    Plan plan;

    @Column(nullable = false)
    String stripeCustomerId;

    @Column(nullable = false)
    String stripeSubscriptionId;

    @Column(nullable = false)
    Instant currentPeriodStart;

    @Column(nullable = false)
    Instant currentPeriodEnd;

    @Column(nullable = false)
    Boolean cancelAtPeriodEnd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    SubscriptionStatus status;

    @Column(nullable = false)
    Boolean active;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;
}
