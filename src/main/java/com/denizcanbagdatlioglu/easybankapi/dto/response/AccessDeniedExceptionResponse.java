package com.denizcanbagdatlioglu.easybankapi.dto.response;

public record AccessDeniedExceptionResponse(
        String uri,
        String error
) {
}
