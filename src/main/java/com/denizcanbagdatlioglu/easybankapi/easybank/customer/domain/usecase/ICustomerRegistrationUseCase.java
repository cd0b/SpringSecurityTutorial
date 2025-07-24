package com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;

import java.util.Optional;

public interface ICustomerRegistrationUseCase {
    Optional<Customer> registerCustomer(Customer customer);
}
