package com.denizcanbagdatlioglu.easybankapi.api.registration.configuration;

import com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security.BaseHttpSecurityConfigurationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class RegistrationSecurityConfiguration {

    private final BaseHttpSecurityConfigurationProvider baseHttpSecurityConfigurationProvider;

    @Bean("registrationSecurityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
        return baseHttpSecurityConfigurationProvider
                .baseHttpSecurity(http)
                .securityMatcher("/api/registration")
                .authorizeHttpRequests(
                        request -> request.requestMatchers(HttpMethod.POST, "/api/registration").permitAll()
                )
                .build();
    }
}
