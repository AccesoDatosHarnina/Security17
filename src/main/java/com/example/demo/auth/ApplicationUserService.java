package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService<x> implements UserDetailsService {

    @Autowired
    private final ApplicationUserDAO applicationUserDAO;

    public ApplicationUserService(@Qualifier("mock") ApplicationUserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDAO.selectApplicationUserByName(username).orElseThrow(
                ()->new UsernameNotFoundException(username+ " not found")
        );
    }
}
