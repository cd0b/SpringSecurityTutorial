package com.denizcanbagdatlioglu.easybankapi.api.registration.configuration;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.application.CustomerRegisterationService;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.port.ICustomerRegistrationPort;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase.ICustomerRegistrationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrationConfiguration {

    @Bean
    public ICustomerRegistrationUseCase customerRegistrationUseCase(ICustomerRegistrationPort customerRegistrationPort) {
        return new CustomerRegisterationService(customerRegistrationPort);
    }
}
