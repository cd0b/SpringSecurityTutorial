package com.denizcanbagdatlioglu.easybankapi.api.registration.mapper;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.Password;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordMapper {

    private final PasswordEncoder passwordEncoder;

    @Named("toPassword")
    public Password toPassword(String password) {
        String encoded = passwordEncoder.encode(password);
        return new Password(encoded);
    }
}