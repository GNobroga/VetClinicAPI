package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.AddressService;
import com.veterinary.care.api.application.models.RecordAddressWithPersonId;
import com.veterinary.care.api.domain.entities.AddressEntity;
import com.veterinary.care.api.domain.projection.AddressProjection;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endereços", description = "CRUD para endereços")
@RequestMapping("/api/v1/enderecos")
@RestController
public class AddressController extends GenericController<AddressEntity, RecordAddressWithPersonId, AddressProjection, AddressService> {

}
