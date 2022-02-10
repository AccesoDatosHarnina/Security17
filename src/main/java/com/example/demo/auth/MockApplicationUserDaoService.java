package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRol.ADMIN;
import static com.example.demo.security.ApplicationUserRol.GUEST;

@Repository("mock")
public class MockApplicationUserDaoService implements ApplicationUserDAO{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByName(String name) {
        Optional<ApplicationUser> appuser = getApplicationUser().stream()
                .filter((applicationUser ->{
                    System.err.println(applicationUser.getUsername());
                   return name.equals(applicationUser.getUsername());
                }
                ))
                .findFirst();
        return appuser;
    }
    private List<ApplicationUser> getApplicationUser(){
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        applicationUsers.add(new ApplicationUser
                ("luis",
                        passwordEncoder.encode("123"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true)
        );
        applicationUsers.add(new ApplicationUser
                ("jose",
                        passwordEncoder.encode("321"),
                        GUEST.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true)
        );
        return applicationUsers;
    }
}
