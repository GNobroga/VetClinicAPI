package com.veterinary.care.api.application.validators.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.veterinary.care.api.application.validators.DateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Constraint(validatedBy = DateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDate {

    String message() default "Invalid date";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
