package com.lp.projects.lovable_clone.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectPermission {

    EDIT("project:edit"),
    VIEW("project:view"),
    DELETE("project:delete"),

    VIEW_MEMBERS("project_members:view"),
    MANAGE_MEMBERS("project_members:manage");

    private final String value;
}
