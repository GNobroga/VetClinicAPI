package com.veterinary.care.api.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.veterinary.care.api.application.exceptions.UnauthorizedException;
import com.veterinary.care.api.application.interfaces.AuthenticationService;
import com.veterinary.care.api.application.models.UsernameAndPassword;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public String authenticate(@RequestBody @Valid UsernameAndPassword usernameAndPassword) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            usernameAndPassword.username(), 
            usernameAndPassword.password());
            
            Authentication authentication = authenticationManager.authenticate(token);

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.createObjectNode();

            ObjectNode objectNode = (ObjectNode) rootNode;

            objectNode.put("token", service.authenticate(authentication));

            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (Exception ex) {
            throw new UnauthorizedException("Usuário ou senha incorretos");
        }
    }


}
