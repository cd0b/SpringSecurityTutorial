package com.denizcanbagdatlioglu.easybankapi.api.common.configuration.security;

import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.usecase.IFindCustomerByEmailUseCase;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.Email;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final IFindCustomerByEmailUseCase findCustomerByEmailUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Email email = new Email(username);
        Optional<Customer> customerMaybe = findCustomerByEmailUseCase.findCustomerByEmail(email);
        return customerMaybe.map(
                customer -> User.withUsername(customer.getEmail().value())
                        .password(customer.getPassword().value())
                        .roles(customer.getRoles().stream().map(Role::value).toArray(String[]::new))
                        .build()
        ).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
