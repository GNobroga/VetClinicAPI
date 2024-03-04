package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.utils.ResponseHandler;
import com.veterinary.care.api.application.utils.ResponseHandler.Status;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/**")
public class DefaultController {

    @Operation(hidden = true)
    @GetMapping
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseHandler<String> get() {
        var response = new ResponseHandler<>("Rota não definida");
        response.setCode(HttpStatus.NOT_FOUND);
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

}
