package com.veterinary.care.api.application.validators;

import com.veterinary.care.api.application.validators.constraints.CheckDocument;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckCpfValidator implements ConstraintValidator<CheckDocument, String> {

    @Override
    public boolean isValid(String document, ConstraintValidatorContext context) {
        var isDocumentValid = isCPF(document) || isCNPJ(document);

        if (!isDocumentValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Documento inválido, não é CPF e nem CNPJ.")
                    .addConstraintViolation();
        }
        return isDocumentValid;
    }

    private static boolean isCPF(String document) {
        document = document.replaceAll("[^\\d]", "");

        if (document.length() != 11 || document.matches("(\\d)\\1{10}")) 
            return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(document.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);

        if (firstDigit > 9) {
            firstDigit = 0;
        }

        sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(document.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);

        if (secondDigit > 9) {
            secondDigit = 0;
        }

        return Character.getNumericValue(document.charAt(9)) == firstDigit
                && Character.getNumericValue(document.charAt(10)) == secondDigit;
    }

    public static boolean isCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            return false;
        }

        int sum = 0;
        int weight = 5;
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weight;
            weight = (weight == 2) ? 9 : weight - 1;
        }
        int remainder = sum % 11;
        int digit1 = (remainder < 2) ? 0 : 11 - remainder;

        if (Character.getNumericValue(cnpj.charAt(12)) != digit1) {
            return false;
        }

        sum = 0;
        weight = 6;
        for (int i = 0; i < 13; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weight;
            weight = (weight == 2) ? 9 : weight - 1;
        }
        remainder = sum % 11;
        int digit2 = (remainder < 2) ? 0 : 11 - remainder;

        return Character.getNumericValue(cnpj.charAt(13)) == digit2;
    }
}
