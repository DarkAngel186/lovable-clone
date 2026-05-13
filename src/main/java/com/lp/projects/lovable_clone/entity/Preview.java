package com.lp.projects.lovable_clone.entity;

import com.lp.projects.lovable_clone.enums.PreviewStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

//@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preview {

    Long id;

    Project project;

    String namespace;

    String podName;

    String previewUrl;

    PreviewStatus status;

    Instant startedAt;

    Instant terminatedAt;

    Instant createdAt;
}
