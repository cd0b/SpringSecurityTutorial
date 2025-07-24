package com.denizcanbagdatlioglu.easybankapi.api.registration.dto;

public record RegistrationResponse(
        String name,
        String email,
        String phoneNumber,
        String roles
) {
}
