package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
    //El map sirve para
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissionsAuth = getPermissions().stream()
                //el map crea una nueva lista donde sustituye cada permiso por una authority de tipo simple
                //recuerda que getPermision() te da "student:read" o algo asi
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        //le add el usuario actual de la enumeracion y volvemos al ApllicationSecurityConfig
        permissionsAuth.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissionsAuth;
    }
}
