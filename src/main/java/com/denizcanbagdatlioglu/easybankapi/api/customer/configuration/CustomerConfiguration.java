package com.denizcanbagdatlioglu.easybankapi.api.customer.configuration;

import com.denizcanbagdatlioglu.easybankapi.api.customer.mapper.CustomerMapper;
import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.CustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.jparepository.JpaCustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.easybank.common.valueobject.ID;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.application.FindCustomerByEmailService;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port.ICustomerRegistrationPort;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port.IFindCustomerByEmailPort;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase.IFindCustomerByEmailUseCase;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.*;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Configuration
public class CustomerConfiguration {

    @Bean
    public CommandLineRunner createAdminCustomer(PasswordEncoder passwordEncoder, ICustomerRegistrationPort customerRepository) {
        return args -> {
            Customer customer = new Customer(
                    new ID(null),
                    new Name("admin"),
                    new Email("admin@easybank.com"),
                    new PhoneNumber("+90 555 555 5555"),
                    new Password(passwordEncoder.encode("admin")),
                    Set.of(new Role("ADMIN")),
                    new DateValueObject(Date.from(Instant.now()))
            );
            customerRepository.registerCustomer(customer);
        };
    }

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
