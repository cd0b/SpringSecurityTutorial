package com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject;

public record Email(
        String value
) {

    public boolean isValid() {
        return value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z]+$");
    }

}
