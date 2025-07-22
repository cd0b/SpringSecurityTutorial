package com.denizcanbagdatlioglu.easybankapi.application.user.domain.ports;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Email;

import java.util.Optional;

public interface ICustomerRepository {
    public Optional<Customer> findCustomerByEmail(Email email);
    public Optional<Customer> registerCustomer(Customer customer);
}
