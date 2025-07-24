package com.denizcanbagdatlioglu.easybankapi.api.login.configuration;

import com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security.BaseHttpSecurityConfigurationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class LoginSecurityConfiguration {

    private final BaseHttpSecurityConfigurationProvider baseHttpSecurityConfigurationProvider;

    @Bean("loginSecurityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   @Qualifier("loginAuthenticationManager")
                                                   AuthenticationManager authenticationManager) throws Exception {
        return baseHttpSecurityConfigurationProvider
                .baseHttpSecurity(http)
                .securityMatcher("/api/login")
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        request -> request.anyRequest().authenticated()
                )
                .authenticationManager(authenticationManager)
                .build();
    }

    @Bean("loginAuthenticationManager")
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider) {
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(@Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
                                                                    PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}
