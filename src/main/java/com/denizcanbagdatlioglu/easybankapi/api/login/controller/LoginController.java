package com.denizcanbagdatlioglu.easybankapi.api.login.controller;

import com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security.JwtUtil;
import com.denizcanbagdatlioglu.easybankapi.api.login.dto.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<LoginResponse> login(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String base64Credentials = authorizationHeader.substring(6);
        byte[] credentialsDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credentialsDecoded, StandardCharsets.UTF_8);
        String[] values = credentials.split(":", 2);
        String email = values[0];

        String token = jwtUtil.generateJwtToken(email);
        return ResponseEntity.ok(new LoginResponse(token));
    }


}
