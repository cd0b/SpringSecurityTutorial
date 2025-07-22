package com.denizcanbagdatlioglu.easybankapi.repository;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Email;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.ports.ICustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.repository.jparepository.ICustomerJPARepository;
import com.denizcanbagdatlioglu.easybankapi.repository.model.CustomerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository implements ICustomerRepository {
    private final ICustomerJPARepository customerJPARepository;

    @Override
    public Optional<Customer> findCustomerByEmail(Email email) {
        Optional<CustomerModel> model = customerJPARepository.findByEmail(email.getContent());
        return model.map( m -> new Customer(
                m.getId(),
                new Email(m.getEmail()),
                m.getPassword(),
                m.getRole()
        ));
    }

    @Override
    public Optional<Customer> registerCustomer(Customer customer) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setEmail(customer.getEmail().getContent());
        customerModel.setPassword(customer.getPassword());
        customerModel.setRole(customer.getRoles().toString());
        CustomerModel savedCustomerModel = customerJPARepository.save(customerModel);
        return savedCustomerModel.getId() > 0 ? Optional.of(new Customer(
                savedCustomerModel.getId(),
                new Email(savedCustomerModel.getEmail()),
                savedCustomerModel.getPassword(),
                savedCustomerModel.getRole()
        )) : Optional.empty();
    }
}
