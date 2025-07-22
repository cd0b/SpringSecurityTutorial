package com.denizcanbagdatlioglu.easybankapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @GetMapping
    public String getLoans() {
        return "My Loan";
    }
}
