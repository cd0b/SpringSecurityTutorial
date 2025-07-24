package com.denizcanbagdatlioglu.easybankapi.api.customer.configuration;

import com.denizcanbagdatlioglu.easybankapi.api.customer.mapper.CustomerMapper;
import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.CustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.jparepository.JpaCustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.application.FindCustomerByEmailService;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port.IFindCustomerByEmailPort;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase.IFindCustomerByEmailUseCase;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    public IFindCustomerByEmailUseCase findCustomerByEmailUseCase(IFindCustomerByEmailPort findCustomerByEmailPort) {
        return new FindCustomerByEmailService(findCustomerByEmailPort);
    }

    @Bean
    public CustomerRepository customerRepository(JpaCustomerRepository jpaCustomerRepository, CustomerMapper customerMapper) {
        return new CustomerRepository(jpaCustomerRepository, customerMapper);
    }

    @Bean
    public CustomerMapper customerMapper() {
        return Mappers.getMapper(CustomerMapper.class);
    }

}
