package com.denizcanbagdatlioglu.easybankapi.api.securitytest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/securitytest")
public class SecurityController {

    @GetMapping("/authentication")
    public String authenticationTest() {
        return "SUCCESS: Authentication Test";
    }

    @GetMapping("/authorization")
    public String authorizationTest() {
        return "SUCCESS: Authorization Test";
    }

}
