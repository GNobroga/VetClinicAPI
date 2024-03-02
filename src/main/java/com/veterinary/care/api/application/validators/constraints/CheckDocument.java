package com.veterinary.care.api.application.validators.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.veterinary.care.api.application.validators.CheckCpfValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckCpfValidator.class)
@Documented
public @interface CheckDocument {

    String message() default "Documento Inválido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}