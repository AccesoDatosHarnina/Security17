package com.example.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.demo.security.ApplicationUserPermission.STUDENT_READ;
import static com.example.demo.security.ApplicationUserPermission.STUDENT_WRITE;

public enum ApplicationUserRol {
    //usar SEts me obliga a importar la dependencia GUAVA
    ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE)),
    GUEST(Sets.newHashSet(STUDENT_READ));
    private final Set<ApplicationUserPermission> permissions;
    ApplicationUserRol(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
