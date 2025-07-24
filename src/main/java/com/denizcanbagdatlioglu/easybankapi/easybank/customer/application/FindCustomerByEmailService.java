package com.denizcanbagdatlioglu.easybankapi.easybank.customer.application;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port.IFindCustomerByEmailPort;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase.IFindCustomerByEmailUseCase;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.Email;

import java.util.Optional;

public class FindCustomerByEmailService implements IFindCustomerByEmailUseCase {
    private final IFindCustomerByEmailPort findCustomerByEmailPort;

    public FindCustomerByEmailService(IFindCustomerByEmailPort findCustomerByEmailPort) {
        this.findCustomerByEmailPort = findCustomerByEmailPort;
    }

    @Override
    public Optional<Customer> findCustomerByEmail(Email email) {
        if(email.isValid()) {
            return findCustomerByEmailPort.findCustomerByEmail(email);
        }
        return Optional.empty();
    }
}
