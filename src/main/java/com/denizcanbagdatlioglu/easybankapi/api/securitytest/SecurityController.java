package com.denizcanbagdatlioglu.easybankapi.api.securitytest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/securitytest")
    public String test() {
        return "test";
    }

}
