package com.denizcanbagdatlioglu.easybankapi.application.user.service;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.ports.ICustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IRegisterNewCustomerUseCase;

import java.util.Optional;

public class RegisterNewCustromerApplicationService implements IRegisterNewCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public RegisterNewCustromerApplicationService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> registerCustomer(Customer customer) {
        if(!customer.getEmail().isValid()) return Optional.empty();
        Optional<Customer> existingCustomer = customerRepository.findCustomerByEmail(customer.getEmail());
        return existingCustomer.isEmpty() ? customerRepository.registerCustomer(customer) : Optional.empty();
    }
}
