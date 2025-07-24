package com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.Email;

import java.util.Optional;

public interface IFindCustomerByEmailUseCase {
    Optional<Customer> findCustomerByEmail(Email email);
}
