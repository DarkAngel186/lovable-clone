package com.lp.projects.lovable_clone.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String stripePriceId;

    @Column(nullable = false)
    Integer maxProjects;

    @Column(nullable = false)
    Integer maxTokensPerDay;

    @Column(nullable = false)
    Integer maxPreviews;

    @Column(nullable = false)
    Boolean unlimitedAi;

    @Column(nullable = false)
    Boolean active;
}
