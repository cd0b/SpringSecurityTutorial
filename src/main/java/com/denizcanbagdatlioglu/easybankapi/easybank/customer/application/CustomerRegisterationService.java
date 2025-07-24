package com.denizcanbagdatlioglu.easybankapi.easybank.customer.application;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port.ICustomerRegistrationPort;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase.ICustomerRegistrationUseCase;

import java.util.Optional;

public class CustomerRegisterationService implements ICustomerRegistrationUseCase {

    private final ICustomerRegistrationPort customerRegistrationPort;

    public CustomerRegisterationService(ICustomerRegistrationPort customerRegistrationPort) {
        this.customerRegistrationPort = customerRegistrationPort;
    }

    @Override
    public Optional<Customer> registerCustomer(Customer customer, String passwordConfirmation) {
        if(!customer.getEmail().isValid()) return Optional.empty();
        if(!customer.getPassword().confirm(passwordConfirmation)) return Optional.empty();

        return customerRegistrationPort.registerCustomer(customer);
    }
}
