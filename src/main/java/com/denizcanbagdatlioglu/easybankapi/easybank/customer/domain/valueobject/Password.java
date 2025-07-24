package com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject;

public record Password(
        String value
) {
    public boolean confirm(String confirmation) {
        return true;
    }
}
