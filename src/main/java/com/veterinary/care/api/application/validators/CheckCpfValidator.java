package com.veterinary.care.api.application.validators;


import com.veterinary.care.api.application.validators.constraints.CheckCpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class CheckCpfValidator implements ConstraintValidator<CheckCpf, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);

        if (firstDigit > 9) {
            firstDigit = 0;
        }

        sum = 0;
        
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);

        if (secondDigit > 9) {
            secondDigit = 0;
        }

        var result = Character.getNumericValue(cpf.charAt(9)) == firstDigit && Character.getNumericValue(cpf.charAt(10)) == secondDigit;

        return result;
    }
    
}
