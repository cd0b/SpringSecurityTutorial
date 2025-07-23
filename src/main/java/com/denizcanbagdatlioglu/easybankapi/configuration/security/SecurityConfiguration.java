package com.denizcanbagdatlioglu.easybankapi.configuration.security;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IFindCustomerByEmailUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                          AuthenticationEntryPoint authenticationEntryPoint,
                                                          AccessDeniedHandler accessDeniedHandler) throws Exception {
        return http
                .redirectToHttps(Customizer.withDefaults())
                .csrf(c -> c.disable())
                .httpBasic(c -> c.disable())
                .formLogin(c -> c.disable())
                .logout(c -> c.disable())
                .authorizeHttpRequests(
                        c -> c
                                .requestMatchers("/api/contact",
                                        "/api/notice",
                                        "/api/registration",
                                        "/api/login").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        c -> c
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(
                        c -> c
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(HandlerExceptionResolver handlerExceptionResolver) {
        return new CustomAccessDeniedHandler(handlerExceptionResolver);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(HandlerExceptionResolver handlerExceptionResolver) {
        return new CustomAuthenticationEntryPoint(handlerExceptionResolver);
    }

    @Bean
    public UserDetailsService userDetailsService(IFindCustomerByEmailUseCase findCustomerByEmailUseCase) {
        return new EasyBankUserDetailService(findCustomerByEmailUseCase);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider customAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder);
    }
}
