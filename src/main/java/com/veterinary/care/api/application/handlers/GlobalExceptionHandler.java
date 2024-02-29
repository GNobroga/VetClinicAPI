package com.veterinary.care.api.application.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.veterinary.care.api.application.utils.ResponseHandler;
import com.veterinary.care.api.application.utils.ResponseHandler.ResponseBody;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBody handlerMethodArgumentValidaException(MethodArgumentNotValidException exception) {
        List<String> details = new ArrayList<>();
       
        for(var error: exception.getAllErrors()) {
            details.add(error.getDefaultMessage());
            }
        
        return ResponseHandler.isFailure("Dados inválidos", details);
    }
}
