package com.denizcanbagdatlioglu.easybankapi.dto.response;

public record AuthorizationExceptionResponse(
        String username,
        String error
) {
}
