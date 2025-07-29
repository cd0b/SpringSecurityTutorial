package com.denizcanbagdatlioglu.easybankapi.api.registration.mapper;

import com.denizcanbagdatlioglu.easybankapi.api.registration.dto.RegistrationRequest;
import com.denizcanbagdatlioglu.easybankapi.api.registration.dto.RegistrationResponse;
import com.denizcanbagdatlioglu.easybankapi.easybank.common.valueobject.ID;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PasswordMapper.class})
public interface RegistrationDTOMapper {

    @Mapping(target = "id", expression = "java(defaultID())")
    @Mapping(source = "name", target = "name", qualifiedByName = "toName")
    @Mapping(source = "email", target = "email", qualifiedByName = "toEmail")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "toPhone")
    @Mapping(source = "password", target = "password", qualifiedByName = "toPassword")
    @Mapping(target = "roles", expression = "java(defaultRoles())")
    @Mapping(target = "creationDate", expression = "java(currentDate())")
    Customer toEntity(RegistrationRequest request);

    @Mapping(source = "name", target = "name", qualifiedByName = "fromName")
    @Mapping(source = "email", target = "email", qualifiedByName = "fromEmail")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "fromPhone")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "fromRoles")
    RegistrationResponse toDTO(Customer customer);

    @Named("toName")
    default Name toName(String name) {
        return new Name(name);
    }

    @Named("toEmail")
    default Email toEmail(String email) {
        return new Email(email);
    }

    @Named("toPhone")
    default PhoneNumber toPhone(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    @Named("fromName")
    default String fromName(Name name) {
        return name.value();
    }

    @Named("fromEmail")
    default String fromEmail(Email email) {
        return email.value();
    }

    @Named("fromPhone")
    default String fromPhone(PhoneNumber phoneNumber) {
        return phoneNumber.value();
    }

    @Named("fromPassword")
    default String fromPassword(Password password) {
        return password.value();
    }

    @Named("fromRoles")
    default String fromRoles(Set<Role> roles) {
        return roles.stream().map(Role::value).collect(Collectors.joining(","));
    }

    default ID defaultID() {
        return new ID(null);
    }

    default Set<Role> defaultRoles() {
        return Set.of(new Role("USER"));
    }

    default DateValueObject currentDate() {
        return new DateValueObject(Date.from(Instant.now()));
    }
}
