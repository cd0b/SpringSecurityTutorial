package com.denizcanbagdatlioglu.easybankapi.repository.jparepository;

import com.denizcanbagdatlioglu.easybankapi.repository.model.CustomerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerJPARepository extends CrudRepository<CustomerModel, Long> {
    Optional<CustomerModel> findByEmail(String email);
}
