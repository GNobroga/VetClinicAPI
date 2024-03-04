package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.RoleService;
import com.veterinary.care.api.application.models.RecordRole;
import com.veterinary.care.api.domain.entities.RoleEntity;
import com.veterinary.care.api.domain.projection.RoleProjection;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController extends GenericController<RoleEntity, RecordRole, RoleProjection, RoleService> {

}
