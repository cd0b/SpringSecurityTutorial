package com.denizcanbagdatlioglu.easybankapi.exception;

import com.denizcanbagdatlioglu.easybankapi.dto.response.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<CustomerRegistrationResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomerRegistrationResponse("", "", ex.getLocalizedMessage()));
    }

    @ExceptionHandler(exception = AccessDeniedException.class, produces = "application/json")
    public ResponseEntity<AccessDeniedExceptionResponse> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
        AccessDeniedExceptionResponse response = new AccessDeniedExceptionResponse(
                request.getRequestURI(),
                ex.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    @ExceptionHandler(exception = SessionAuthenticationException.class, produces = "application/json")
    public ResponseEntity<ExceptionResponse> handleSessionAuthenticationException(SessionAuthenticationException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<AuthorizationExceptionResponse> handleAuthenticationException(AuthenticationException ex) {
        AuthorizationExceptionResponse response = new AuthorizationExceptionResponse(
                ex.getAuthenticationRequest().getName(),
                ex.getLocalizedMessage()
        );
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestBodyNotValidResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrorMap = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
                fieldError -> fieldError.getField(),
                fieldError -> fieldError.getDefaultMessage()
        ));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RequestBodyNotValidResponse(fieldErrorMap));
    }
}
