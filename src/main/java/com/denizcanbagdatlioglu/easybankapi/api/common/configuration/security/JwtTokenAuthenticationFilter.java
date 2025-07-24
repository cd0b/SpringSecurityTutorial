package com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!isAuthenticated()) {
            String authorizationHeader = request.getHeader("Authorization");

            if (isBearerToken(authorizationHeader)) {
                String token = extractToken(authorizationHeader);
                JwtAuthentication jwtAuthentication = (JwtAuthentication) authenticationManager.authenticate(new JwtAuthentication(token));
                jwtAuthentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void successAuthentication(UserDetails userDetails, WebAuthenticationDetails webAuthenticationDetails) {

    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

    private boolean isBearerToken(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }
}
