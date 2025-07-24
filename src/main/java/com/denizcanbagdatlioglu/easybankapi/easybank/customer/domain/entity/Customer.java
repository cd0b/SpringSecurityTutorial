package com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.entity;

import com.denizcanbagdatlioglu.easybankapi.easybank.common.valueobject.ID;
import com.denizcanbagdatlioglu.easybankapi.easybank.customer.domain.valueobject.*;

import java.util.Set;

public class Customer {

    private ID id;
    private Name name;
    private Email email;
    private PhoneNumber phoneNumber;
    private Password password;
    private Set<Role> roles;
    private DateValueObject creationDate;

    public Customer(ID id, Name name, Email email, PhoneNumber phoneNumber, Password password, Set<Role> roles, DateValueObject creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = roles;
        this.creationDate = creationDate;
    }

    public ID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Password getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public DateValueObject getCreationDate() {
        return creationDate;
    }
}
