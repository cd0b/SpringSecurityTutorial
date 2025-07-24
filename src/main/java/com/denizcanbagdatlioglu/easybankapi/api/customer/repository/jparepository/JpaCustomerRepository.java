package com.denizcanbagdatlioglu.easybankapi.api.customer.repository.jparepository;

import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.model.CustomerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends CrudRepository<CustomerModel, String> {
    public Optional<CustomerModel> findCustomerByEmail(String email);
}
