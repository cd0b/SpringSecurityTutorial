package com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Set;

@RequiredArgsConstructor
public class JwtAuthentication implements Authentication {

    private final String token;
    private final String username;
    private final Set<? extends GrantedAuthority> authorities;

    private boolean authenticated = false;
    private WebAuthenticationDetails webAuthenticationDetails;

    public JwtAuthentication(String token) {
        this.token = token;
        this.username = null;
        this.authorities = null;
    }

    public JwtAuthentication(String username, Set<? extends GrantedAuthority> authorities) {
        this.token = null;
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getCredentials() {
        return token;
    }

    @Override
    public WebAuthenticationDetails getDetails() {
        return webAuthenticationDetails;
    }

    public void setDetails(WebAuthenticationDetails webAuthenticationDetails) {
        this.webAuthenticationDetails = webAuthenticationDetails;
    }

    @Override
    public String getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return username;
    }
}
