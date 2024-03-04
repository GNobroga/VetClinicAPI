package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.VeterinaryService;
import com.veterinary.care.api.application.models.RecordVeterinary;
import com.veterinary.care.api.domain.entities.VeterinaryEntity;
import com.veterinary.care.api.domain.projection.VeterinaryProjection;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Veterinarios", description = "CRUD para veterinarios")
@RequestMapping("/api/v1/veterinarios")
@RestController
public class VeterinaryController
        extends GenericController<VeterinaryEntity, RecordVeterinary, VeterinaryProjection, VeterinaryService> {

}
