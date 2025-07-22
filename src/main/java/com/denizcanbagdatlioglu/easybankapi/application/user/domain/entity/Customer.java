package com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity;

import java.util.List;

public class Customer {
    private Long id;
    private Email email;
    private String password;
    private String roles;

    public Customer(Long id, Email email, String password, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public List<String> getRoles() {
        String[] roles = this.roles.split(",");
        return List.of(roles);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + roles + '\'' +
                '}';
    }
}
