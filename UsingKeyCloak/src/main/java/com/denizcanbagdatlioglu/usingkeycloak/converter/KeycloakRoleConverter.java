package com.denizcanbagdatlioglu.usingkeycloak.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public final Collection<GrantedAuthority> convert(Jwt jwt) {
        return Optional.ofNullable((Map<String, Object>) jwt.getClaims().get("realm_access"))
                .flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")))
                .orElse(List.of())
                .stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
