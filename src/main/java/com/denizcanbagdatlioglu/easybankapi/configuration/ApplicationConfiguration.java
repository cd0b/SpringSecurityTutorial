package com.denizcanbagdatlioglu.easybankapi.configuration;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.ports.ICustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IFindCustomerByEmailUseCase;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IRegisterNewCustomerUseCase;
import com.denizcanbagdatlioglu.easybankapi.application.user.service.FindCustomerByEmailApplicationService;
import com.denizcanbagdatlioglu.easybankapi.application.user.service.RegisterNewCustromerApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
//@EnableJpaRepositories(basePackages = "com.denizcanbagdatlioglu.easybankapi.repository.jparepository")
//@EntityScan(basePackages = "com.denizcanbagdatlioglu.easybankapi.repository.model")
public class ApplicationConfiguration {

    @Bean
    public IFindCustomerByEmailUseCase findCustomerByEmailUseCase(ICustomerRepository customerRepository) {
        return new FindCustomerByEmailApplicationService(customerRepository);
    }

    @Bean
    public IRegisterNewCustomerUseCase registerNewCustomerUseCase(ICustomerRepository customerRepository) {
        return new RegisterNewCustromerApplicationService(customerRepository);
    }

}
