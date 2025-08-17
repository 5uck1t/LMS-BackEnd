package com.example.demo.security;

import java.io.IOException;
import java.util.Map;

import com.example.demo.utils.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationFilterBean extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = ((HttpServletRequest) request).getHeader("Authorization");
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                if (tokenUtils.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    String username = tokenUtils.getUsername(token);
                    UserDetails user = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                            user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (io.jsonwebtoken.security.SignatureException e) {
                    sendJsonError(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT signature");
                return;
            } catch (io.jsonwebtoken.ExpiredJwtException e) {
                sendJsonError(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, "JWT token expired");
                return;
            } catch (Exception e) {
                sendJsonError(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void sendJsonError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String json = objectMapper.writeValueAsString(Map.of("error", message));
        response.getWriter().write(json);
    }

}

