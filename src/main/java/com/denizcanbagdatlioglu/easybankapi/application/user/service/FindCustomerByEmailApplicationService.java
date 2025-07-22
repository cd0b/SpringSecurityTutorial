package com.denizcanbagdatlioglu.easybankapi.application.user.service;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Email;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.ports.ICustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IFindCustomerByEmailUseCase;

import java.util.Optional;

public class FindCustomerByEmailApplicationService implements IFindCustomerByEmailUseCase {

    private final ICustomerRepository customerRepository;

    public FindCustomerByEmailApplicationService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findCustomerByEmail(Email email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
