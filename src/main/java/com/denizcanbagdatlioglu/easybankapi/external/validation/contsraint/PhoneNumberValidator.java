package com.denizcanbagdatlioglu.easybankapi.external.validation.contsraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_NUMBER_REGEX = "^(\\+90|0)?\\s?\\d{3}\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value == null) ||
                (!value.isBlank() && value.matches(PHONE_NUMBER_REGEX));
    }
}
