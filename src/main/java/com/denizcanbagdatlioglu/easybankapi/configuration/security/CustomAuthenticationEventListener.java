package com.denizcanbagdatlioglu.easybankapi.configuration.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

public class CustomAuthenticationEventListener {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        System.out.println("DENOOOOO SUCCESS");
        System.out.println(event.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
        System.out.println("DENOOOOO FAILURE");
        System.out.println(event.getException().getMessage());
    }
}
