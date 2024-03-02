package com.veterinary.care.api.application.handlers;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.utils.ResponseHandler;
import com.veterinary.care.api.application.utils.ResponseHandler.Status;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
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

    @ExceptionHandler(NegociationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseHandler<Object> handleNegociationException(NegociationException ex) {
        var responseHandler = new ResponseHandler<>();
        responseHandler.addMessage(ex.getMessage());
        responseHandler.setStatus(Status.ERROR);
        responseHandler.setCode(HttpStatus.BAD_REQUEST);
        return responseHandler;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseHandler<Object> defaultHandleException(Exception ex) {
        var responseHandler = new ResponseHandler<>();
        responseHandler.addMessage("Error interno");
        responseHandler.setStatus(Status.ERROR);
        responseHandler.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseHandler;
    }

}
