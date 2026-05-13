package com.lp.projects.lovable_clone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

//@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Project project;

    @Column(nullable = false)
    User createdBy;

    @Column(nullable = false)
    User updatedBy;

    @Column(nullable = false)
    String path;

    @Column(nullable = false)
    String minioObjectKey;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;
}
