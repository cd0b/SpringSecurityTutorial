package com.denizcanbagdatlioglu.easybankapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRegistrationRequest(
        @Email @NotBlank @NotNull String email,
        @NotBlank @NotNull String password,
        @NotBlank @NotNull String roles
) {
}
