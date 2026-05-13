package com.lp.projects.lovable_clone.entity;

import com.lp.projects.lovable_clone.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectMember {

    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    Project project;

    @ManyToOne
    @MapsId("userId")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;

    Instant invitedAt;

    Instant acceptedAt;
}
