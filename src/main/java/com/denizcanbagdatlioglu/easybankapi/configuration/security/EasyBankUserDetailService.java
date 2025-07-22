package com.denizcanbagdatlioglu.easybankapi.configuration.security;

import com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity.Email;
import com.denizcanbagdatlioglu.easybankapi.application.user.domain.usecases.IFindCustomerByEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class EasyBankUserDetailService implements UserDetailsService {

    private final IFindCustomerByEmailUseCase findCustomerByEmailUseCase;

    @Override
    public UserDetails loadUserByUsername(String emailStr) throws UsernameNotFoundException {
        Email email = new Email(emailStr);
        return findCustomerByEmailUseCase.findCustomerByEmail(email).map(
                c -> User.withUsername(c.getEmail().getContent())
                        .password(c.getPassword())
                        .accountLocked(false)
                        .accountExpired(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .roles(c.getRoles().toArray(new String[0]))
                        .build()
        ).orElseThrow(
                () -> new UsernameNotFoundException("Email is not registered: " + email + "!")
        );
    }
}
