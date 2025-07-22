package com.denizcanbagdatlioglu.easybankapi.configuration.security;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IFindCustomerByEmailUseCase;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.web.servlet.HandlerExceptionResolver;


@SpringBootConfiguration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                          AuthenticationEntryPoint authenticationEntryPoint,
                                                          AccessDeniedHandler accessDeniedHandler,
                                                          InvalidSessionStrategy invalidSessionStrategy) throws Exception {
        return http
                .redirectToHttps(Customizer.withDefaults())
                .csrf(
                        c -> c.disable()
                )
                .authorizeHttpRequests(
                c -> c
                        .requestMatchers("/api/account", "/api/balance", "/api/card", "/api/loan").authenticated()
                        .requestMatchers("/api/contact", "/api/notice", "/api/registration").permitAll()
                )
                .httpBasic(
                        c -> c.authenticationEntryPoint(authenticationEntryPoint)
                )
                .formLogin(
                        c -> c.disable()
                )
                .exceptionHandling(
                        c -> c
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .sessionManagement(
                        c -> c
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                .sessionFixation()
                                .changeSessionId()
                                .invalidSessionStrategy(invalidSessionStrategy)
                                .maximumSessions(1)
                                .maxSessionsPreventsLogin(true)
                )
                .logout(
                        c -> c
                                .logoutUrl("/api/logout")
                                .invalidateHttpSession(true)
                )
                .build();
    }

    @Bean
    public CustomAuthenticationEventListener customAuthenticationEventListener() {
        return new CustomAuthenticationEventListener();
    }

    @Bean
    public InvalidSessionStrategy invalidSessionStrategy(HandlerExceptionResolver handlerExceptionResolver) {
        return new CustomInvalidSessionStrategy(handlerExceptionResolver);
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
