package com.veterinary.care.api.application.validators.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.veterinary.care.api.application.validators.CheckEnumTypeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Constraint(validatedBy = CheckEnumTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckEnumType {
 
    Class<?> className();

    String message() default "Tipo Inválido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ ElementType.FIELD, ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface EnumType {
        Class<?> value();
        String message() default "O tipo é obrigatório";
    } 
    
}
