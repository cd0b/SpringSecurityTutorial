package com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

@RequiredArgsConstructor
public class JwtTokenAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
        String token = jwtAuthentication.getCredentials();
        String userNameFromToken = jwtUtil.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromToken);
        String userNameFromUserDetails = userDetails.getUsername();
        if(jwtUtil.isTokenValid(token, userNameFromUserDetails)) {
            Set<? extends GrantedAuthority> authorities = Set.copyOf(userDetails.getAuthorities());
            JwtAuthentication grantedAuthentication = new JwtAuthentication(userNameFromUserDetails, authorities);
            grantedAuthentication.setAuthenticated(true);
            return grantedAuthentication;
        }
        else {
            throw new BadCredentialsException("Invalid token: " + token);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }
}
