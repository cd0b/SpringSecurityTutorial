package com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.Email;

import java.util.Optional;

public interface IFindCustomerByEmailPort {
    Optional<Customer> findCustomerByEmail(Email email);
}
