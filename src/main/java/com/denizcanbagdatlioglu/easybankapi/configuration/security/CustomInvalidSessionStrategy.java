package com.denizcanbagdatlioglu.easybankapi.configuration.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    private final HandlerExceptionResolver resolver;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        resolver.resolveException(request, response, null, new SessionAuthenticationException("Invalid session or session has expired. Please login again."));
    }
}
