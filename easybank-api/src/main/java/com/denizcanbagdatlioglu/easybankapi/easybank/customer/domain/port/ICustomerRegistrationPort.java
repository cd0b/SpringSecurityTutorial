package com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;

import java.util.Optional;

public interface ICustomerRegistrationPort {
    Optional<Customer> registerCustomer(Customer customer);
}
