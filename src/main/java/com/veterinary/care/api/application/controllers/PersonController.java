package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.PersonService;
import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.domain.entities.PersonEntity;
import com.veterinary.care.api.domain.projection.PersonProjection;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pessoas", description = "CRUD para pessoas")
@RequestMapping("/api/v1/pessoas")
@RestController
public class PersonController extends GenericController<PersonEntity, RecordPerson, PersonProjection, PersonService> {
    
}
