package com.denizcanbagdatlioglu.easybankapi.api.registration.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phoneNumber,
        @NotBlank String password,
        @NotBlank String passwordConfirmation
) {
}
