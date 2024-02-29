package com.veterinary.care.api.application.validators;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.veterinary.care.api.application.enums.AddressType;
import com.veterinary.care.api.application.validators.constraints.CheckAddressType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckAddressTypeValidator implements ConstraintValidator<CheckAddressType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        AddressType[] addressTypes = AddressType.values();
        var values = Arrays.stream(addressTypes).map(x -> x.getValue()).collect(Collectors.toList());

        if (!values.contains(value)) {
            var existingTypes = values.stream().collect(Collectors.joining(","));
            context.buildConstraintViolationWithTemplate("Tipo do endereço não existe, lista de tipos existentes: " + existingTypes).addConstraintViolation();
            context.disableDefaultConstraintViolation();
            return false;
        }

        return true;
    }

}