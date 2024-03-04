package com.veterinary.care.api.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.AttendanceService;
import com.veterinary.care.api.application.models.RecordAttendance;
import com.veterinary.care.api.domain.entities.AttendanceEntity;
import com.veterinary.care.api.domain.projection.AttendanceProjection;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Atendimentos", description = "CRUD para atendimentos")
@RequestMapping("/api/v1/atendimentos")
@RestController
public class AttendanceController
        extends GenericController<AttendanceEntity, RecordAttendance, AttendanceProjection, AttendanceService> {

}
