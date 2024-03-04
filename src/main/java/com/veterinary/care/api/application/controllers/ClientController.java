package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.ClientService;
import com.veterinary.care.api.application.models.RecordClient;
import com.veterinary.care.api.domain.entities.ClientEntity;
import com.veterinary.care.api.domain.projection.ClientProjection;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Client", description = "CRUD para clientes")
@RequestMapping("/api/v1/clientes")
@RestController
public class ClientController extends GenericController<ClientEntity, RecordClient, ClientProjection, ClientService> {
}
