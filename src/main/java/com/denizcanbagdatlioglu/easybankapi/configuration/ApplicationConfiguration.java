package com.denizcanbagdatlioglu.easybankapi.configuration;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.ports.ICustomerRepository;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IFindCustomerByEmailUseCase;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IRegisterNewCustomerUseCase;
import com.denizcanbagdatlioglu.easybankapi.application.user.service.FindCustomerByEmailApplicationService;
import com.denizcanbagdatlioglu.easybankapi.application.user.service.RegisterNewCustromerApplicationService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
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
