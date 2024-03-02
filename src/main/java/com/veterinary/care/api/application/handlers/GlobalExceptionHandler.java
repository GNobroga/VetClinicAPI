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
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Function<ObjectError, String> getDefaultMessage = (obj) -> obj.getDefaultMessage(); 

        var errors = ex.getBindingResult().getAllErrors()
            .stream().map(getDefaultMessage).collect(Collectors.toList());

        Map<String, List<String>> errorsMap = new HashMap<>()
        {
            {
                put("errors", errors);
            }
        };

        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

}
