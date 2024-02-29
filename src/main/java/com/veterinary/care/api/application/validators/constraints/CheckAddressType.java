package com.veterinary.care.api.application.validators.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.veterinary.care.api.application.validators.CheckAddressTypeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Constraint(validatedBy = CheckAddressTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckAddressType {

    String message() default "O tipo endereço não bate com os tipos permitidos";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
}
