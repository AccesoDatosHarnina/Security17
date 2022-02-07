package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.demo.security.ApplicationUserPermission.STUDENT_WRITE;
import static com.example.demo.security.ApplicationUserRol.ADMIN;
import static com.example.demo.security.ApplicationUserRol.GUEST;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/cursos", true);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails luis = User.builder()
                .username("luis")
                .password(passwordEncoder.encode("123"))
                .roles(ADMIN.name())
//                .authorities(ADMIN.getGrantedAuthorities())
//                .authorities(new SimpleGrantedAuthority(STUDENT_WRITE.getPermission()))
                .build();
        UserDetails jose = User.builder()
                .username("jose")
                .password(passwordEncoder.encode("321"))
                .roles(GUEST.name())
//                .authorities(new SimpleGrantedAuthority(STUDENT_WRITE.getPermission()))
                .build();
        return new InMemoryUserDetailsManager(luis, jose);
    }
}
