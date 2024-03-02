package com.veterinary.care.api.application.handlers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.veterinary.care.api.application.utils.ResponseHandler;
import com.veterinary.care.api.application.utils.ResponseHandler.Status;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
    public ResponseHandler<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        Function<ObjectError, String> getDefaultMessage = (obj) -> obj.getDefaultMessage(); 

        var errors = ex.getBindingResult().getAllErrors()
            .stream().map(getDefaultMessage).collect(Collectors.toList());

        var responseHandler = new ResponseHandler<Object>();

        responseHandler.setMessages(errors);
        responseHandler.setStatus(Status.ERROR);
        responseHandler.setCode(HttpStatus.BAD_REQUEST);

        return responseHandler;
    }

}
