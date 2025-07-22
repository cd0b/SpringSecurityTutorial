package com.denizcanbagdatlioglu.easybankapi.dto.response;

public record CustomerRegistrationResponse(
        String email,
        String roles,
        String error
) {
    public CustomerRegistrationResponse(String email, String roles) {
        this(email, roles, "");
    }
}
