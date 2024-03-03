package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.UserService;
import com.veterinary.care.api.application.models.RecordUser;
import com.veterinary.care.api.domain.entities.UserEntity;
import com.veterinary.care.api.domain.projection.UserProjection;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários", description = "CRUD para usuários")
@RequestMapping("/api/v1/pessoa/usuarios")
@RestController
public class UserController extends GenericController<UserEntity, RecordUser, UserProjection, UserService> {

}
