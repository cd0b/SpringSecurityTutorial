package com.denizcanbagdatlioglu.easybankapi.controller;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Email;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IRegisterNewCustomerUseCase;
import com.denizcanbagdatlioglu.easybankapi.dto.request.CustomerRegistrationRequest;
import com.denizcanbagdatlioglu.easybankapi.dto.response.CustomerRegistrationResponse;
import com.denizcanbagdatlioglu.easybankapi.exception.CustomerAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final IRegisterNewCustomerUseCase registerNewCustomerUseCase;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<CustomerRegistrationResponse> registration(@Valid @RequestBody CustomerRegistrationRequest request) {
        Email email = new Email(request.email());
        String password = passwordEncoder.encode(request.password());
        String roles = request.roles();
        Optional<Customer> registeredCustomer = registerNewCustomerUseCase.registerCustomer(new Customer(
                0L, email, password, roles
        ));
        try {
            Customer customer = registeredCustomer.get();
            CustomerRegistrationResponse response = new CustomerRegistrationResponse(
                    customer.getEmail().getContent(), String.join(",", customer.getRoles())
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (NoSuchElementException ex) {
            throw new CustomerAlreadyExistsException("Email is already registerd: " + email);
        }
    }

}
