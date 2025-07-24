package com.denizcanbagdatlioglu.easybankapi.api.customer.repository;

import com.denizcanbagdatlioglu.easybankapi.api.customer.mapper.CustomerMapper;
import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.jparepository.JpaCustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.model.CustomerModel;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port.ICustomerRegistrationPort;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port.IFindCustomerByEmailPort;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerRepository implements IFindCustomerByEmailPort, ICustomerRegistrationPort {

    private final JpaCustomerRepository jpaCustomerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public Optional<Customer> findCustomerByEmail(Email email) {
        return jpaCustomerRepository
                .findCustomerByEmail(email.value())
                .map(customerMapper::toEntity);
    }

    @Override
    public Optional<Customer> registerCustomer(Customer customer) {
        CustomerModel customerModel = jpaCustomerRepository.save(customerMapper.toModel(customer));
        return Optional.of(customerModel).filter(c -> c.getId() != null).map(customerMapper::toEntity);
    }
}
