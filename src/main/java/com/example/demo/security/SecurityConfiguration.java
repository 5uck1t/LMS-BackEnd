package com.example.demo.security;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<String, PasswordEncoder>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(encoders.get("bcrypt"));

        return passwordEncoder;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            String message = "{\"message\": \"Unauthorized: " + authException.getMessage() + "\"}";
            response.getWriter().write(message);
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            String message = "{\"message\": \"Unauthorized: " + accessDeniedException.getMessage() + "\"}";
            System.out.println(message);
            response.getWriter().write(message);
        };
    }

    @Bean
    public AuthenticationFilterBean getAuthenticationFilterBean(AuthenticationConfiguration conf) throws Exception {
        AuthenticationFilterBean authFilter = new AuthenticationFilterBean();
        authFilter.setAuthenticationManager(conf.getAuthenticationManager());
        return authFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration conf) throws Exception {
        http
                .csrf(csfr -> {
                    csfr.disable();
                })
                .sessionManagement(m -> {
                    m.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .addFilterBefore(this.getAuthenticationFilterBean(conf), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

