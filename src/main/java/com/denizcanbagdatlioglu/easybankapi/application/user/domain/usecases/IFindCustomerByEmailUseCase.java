package com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Email;

import java.util.Optional;

public interface IFindCustomerByEmailUseCase {
    Optional<Customer> findCustomerByEmail(Email email);
}
