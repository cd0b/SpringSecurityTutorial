package com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase.IFindCustomerByEmailUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class CommonSecurityConfiguration {

    @Bean
    public BaseHttpSecurityConfigurationProvider baseHttpSecurityConfiguration(AuthenticationEntryPoint authenticationEntryPoint,
                                                                               AccessDeniedHandler accessDeniedHandler,
                                                                               JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter,
                                                                               AuthenticationManager authenticationManager) {
        return new BaseHttpSecurityConfigurationProvider(authenticationEntryPoint,
                accessDeniedHandler,
                jwtTokenAuthenticationFilter,
                authenticationManager);
    }

    @Bean
    @Qualifier("jwtUserDetailsService")
    public UserDetailsService userDetailsService(IFindCustomerByEmailUseCase findCustomerByEmailUseCase) {
        return new JwtUserDetailsService(findCustomerByEmailUseCase);
    }

    @Bean
    public AuthenticationManager authenticationManager(JwtTokenAuthenticationProvider jwtTokenAuthenticationProvider) {
        return new ProviderManager(jwtTokenAuthenticationProvider);
    }

    @Bean
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new JwtTokenAuthenticationFilter(authenticationManager);
    }

    @Bean
    public JwtTokenAuthenticationProvider jwtTokenAuthenticationProvider(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        return new JwtTokenAuthenticationProvider(jwtUtil, userDetailsService);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        return new CommonAuthenticationEntryPoint(resolver);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        return new CommonAccessDeniedHandler(resolver);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
