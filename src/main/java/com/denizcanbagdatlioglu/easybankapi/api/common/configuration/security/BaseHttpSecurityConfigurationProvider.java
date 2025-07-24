package com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@RequiredArgsConstructor
public class BaseHttpSecurityConfigurationProvider {

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;
    private final AuthenticationManager authenticationManager;

    public HttpSecurity baseHttpSecurity(HttpSecurity http) throws Exception {
        return http
                .redirectToHttps(Customizer.withDefaults())
                .csrf(c -> c.disable())
                .httpBasic(c -> c.disable())
                .formLogin(c -> c.disable())
                .logout(c -> c.disable())
                .anonymous(c -> c.disable())
                .sessionManagement(
                        c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(
                        c -> c.authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .authenticationManager(authenticationManager)
//                .authenticationProvider(jwtTokenAuthenticationProvider)
                .addFilterBefore(jwtTokenAuthenticationFilter, AnonymousAuthenticationFilter.class)
                ;
    }

}
