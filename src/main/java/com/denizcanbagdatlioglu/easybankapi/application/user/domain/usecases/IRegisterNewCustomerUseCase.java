package com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Customer;

import java.util.Optional;

public interface IRegisterNewCustomerUseCase {
    Optional<Customer> registerCustomer(Customer customer);
}
