package com.denizcanbagdatlioglu.easybankapi.api.securitytest;

import com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security.BaseHttpSecurityConfigurationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final BaseHttpSecurityConfigurationProvider baseHttpSecurityConfigurationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return baseHttpSecurityConfigurationProvider.baseHttpSecurity(http)
                .securityMatcher("/securitytest")
                .authorizeHttpRequests(
                        c->c.anyRequest().authenticated()
                )
                .build();
    }

}
