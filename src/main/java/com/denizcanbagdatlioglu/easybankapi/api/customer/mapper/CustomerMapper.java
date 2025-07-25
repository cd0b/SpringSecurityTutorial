package com.denizcanbagdatlioglu.easybankapi.api.customer.mapper;

import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.model.CustomerModel;
import com.denizcanbagdatlioglu.easybankapi.api.customer.repository.model.RoleModel;
import com.denizcanbagdatlioglu.easybankapi.easybank.common.valueobject.ID;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity.Customer;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface CustomerMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "toID")
    @Mapping(source = "name", target = "name", qualifiedByName = "toName")
    @Mapping(source = "email", target = "email", qualifiedByName = "toEmail")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "toPhone")
    @Mapping(source = "password", target = "password", qualifiedByName = "toPassword")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "toRoles")
    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "toDate")
    Customer toEntity(CustomerModel customerModel);

    @Mapping(source = "id", target = "id", qualifiedByName = "fromID")
    @Mapping(source = "name", target = "name", qualifiedByName = "fromName")
    @Mapping(source = "email", target = "email", qualifiedByName = "fromEmail")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "fromPhone")
    @Mapping(source = "password", target = "password", qualifiedByName = "fromPassword")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "fromRoles")
    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "fromDate")
    CustomerModel toModel(Customer customer);

    @Named("toID")
    default ID toID(String id) {
        return new ID(id);
    }

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

    @Named("toPassword")
    default Password toPassword(String password) {
        return new Password(password);
    }

    @Named("toRoles")
    default Set<Role> toRoles(Set<RoleModel> roles) {
        return roles.stream()
                .map(RoleModel::getName)
                .map(Role::new)
                .collect(Collectors.toSet());
    }

    @Named("toDate")
    default DateValueObject toDate(Date date) {
        return new DateValueObject(date);
    }

    @Named("fromID")
    default String fromID(ID id) {
        return id.value();
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
    default Set<RoleModel> fromRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::value)
                .map(roleString -> {
                    RoleModel roleModel = new RoleModel();
                    roleModel.setName(roleString);
                    return roleModel;
                })
                .collect(Collectors.toSet());
    }

    @Named("fromDate")
    default Date fromDate(DateValueObject dateValueObject) {
        return dateValueObject.value();
    }

}
