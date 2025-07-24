package com.denizcanbagdatlioglu.easybankapi.api.registration.controller;

import com.denizcanbagdatlioglu.easybankapi.api.registration.dto.RegistrationRequest;
import com.denizcanbagdatlioglu.easybankapi.api.registration.dto.RegistrationResponse;
import com.denizcanbagdatlioglu.easybankapi.api.registration.mapper.RegistrationDTOMapper;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase.ICustomerRegistrationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final ICustomerRegistrationUseCase customerRegistrationUseCase;

    private final RegistrationDTOMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request) {
        Customer customer = mapper.toEntity(request);
        if(!passwordEncoder.matches(request.passwordConfirmation(), customer.getPassword().value()))
            throw new BadCredentialsException("Passwords don't match");
        return customerRegistrationUseCase
                .registerCustomer(customer)
                .map(mapper::toDTO)
                .map(o -> ResponseEntity.status(HttpStatus.CREATED).body(o))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RegistrationResponse("", "", "", "")));
    }

}
