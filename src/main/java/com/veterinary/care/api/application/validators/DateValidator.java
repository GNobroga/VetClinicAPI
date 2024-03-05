package com.veterinary.care.api.application.validators;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.veterinary.care.api.application.validators.constraints.CheckDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<CheckDate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            LocalDateTime.parse(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
