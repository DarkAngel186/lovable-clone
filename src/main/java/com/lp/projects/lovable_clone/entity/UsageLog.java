package com.lp.projects.lovable_clone.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

//@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsageLog {

    Long id;

    User user;

    Project project;

    String action;

    Integer tokensUsed;

    Integer durationMs;

    String metadata; // JSON of {model_used, prompt_used}

    Instant createdAt;
}
