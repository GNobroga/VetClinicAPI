package com.veterinary.care.api.application.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.veterinary.care.api.application.validators.constraints.CheckEnumType;

public class CheckEnumTypeValidator implements ConstraintValidator<CheckEnumType, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Class<?> clazz = value.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (var field: fields) {
            var annotation = field.getAnnotation(CheckEnumType.EnumType.class);

            if (annotation == null)
                continue;

            Class<?> targetClass = annotation.value();

            if (!targetClass.isEnum()) 
                continue;

           
            Object[] constants = targetClass.getEnumConstants();

           try {
                field.setAccessible(true);
                var fieldValue = String.valueOf(field.get(value));
                var exists = Arrays.stream(constants).anyMatch(x -> String.valueOf(x).equals(fieldValue));

                if (!exists) {
                    context.disableDefaultConstraintViolation();
                    var valuesAccepted = Arrays.stream(constants).map(x -> String.valueOf(x)).collect(Collectors.joining(","));
                    context.buildConstraintViolationWithTemplate("O tipo enumerado para %s só aceita os valores: %s".formatted(targetClass.getSimpleName(), valuesAccepted))
                        .addConstraintViolation();
                }

                return exists;
           } catch (Exception error) {}
            
        }
        return false;
    }
    
}
