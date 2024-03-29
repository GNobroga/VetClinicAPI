package com.veterinary.care.api.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NegociationException extends RuntimeException {
    public NegociationException(String message) { super(message); }
}