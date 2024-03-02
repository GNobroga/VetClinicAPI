package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.DogService;
import com.veterinary.care.api.application.models.RecordDog;
import com.veterinary.care.api.domain.entities.DogEntity;
import com.veterinary.care.api.domain.projection.DogProjection;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cachorros", description = "CRUD para cachorros")
@RequestMapping("/api/v1/cachorros")
@RestController
public class DogController extends GenericController<DogEntity, RecordDog, DogProjection, DogService> {

}
