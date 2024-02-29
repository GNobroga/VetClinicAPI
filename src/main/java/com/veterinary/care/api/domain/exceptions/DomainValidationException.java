package com.veterinary.care.api.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainValidationException extends RuntimeException {
    
    public DomainValidationException(String message) {
        super(message);
    }
}
