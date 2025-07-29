package com.denizcanbagdatlioglu.sociallogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class SocialLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialLoginApplication.class, args);
    }

    @Bean
    SecurityFilterChain socialLoginSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .redirectToHttps(Customizer.withDefaults())
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/secured").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults())
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return User.withUsername("user").password("{noop}12345").build();
            }
        };
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository(ClientRegistration googleClientRegistration) {
        return new InMemoryClientRegistrationRepository(googleClientRegistration);
    }

    @Bean
    ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE
                .getBuilder("google")
                .clientId("google-oauth2-client-id")
                .clientSecret("google-oauth2-client-secret")
                .build();
    }

}
