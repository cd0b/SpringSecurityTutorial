package com.denizcanbagdatlioglu.easybankapi.api.common.exception;

public record ExceptionResponse(
        String error,
        String debug
) {

    public ExceptionResponse(String error) {
        this(error, null);
    }
}
