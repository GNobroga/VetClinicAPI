package com.veterinary.care.api.application.utils;

import java.util.Objects;
import java.util.function.Supplier;

import com.veterinary.care.api.application.exceptions.NegociationException;

public class CommonValidation {
    public static void throwExceptionIfInvalidId(Long id, String targetField) {
        if (Objects.isNull(id))
            throw new NegociationException("A identificação do %s é obrigatória".formatted(targetField));
    }

    public static  Supplier<NegociationException> throwEntityNotfound(String name) {
       return () -> new NegociationException("%s não encontrado".formatted(name));
    }

    public static void throwBusinessRuleViolation(String message) {
        throw new NegociationException(message);
    }
}
