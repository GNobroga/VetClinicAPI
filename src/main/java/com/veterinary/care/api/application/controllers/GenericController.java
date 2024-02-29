package com.veterinary.care.api.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.GenericService;
import com.veterinary.care.api.domain.entities.base.BaseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public class GenericController<TEntity extends BaseEntity, TModel, TService extends GenericService<TEntity, TModel>> {

    @Autowired
    private TService service;

    @GetMapping
    @Operation(
        summary = "Retorna lista de objetos dessa entidade", 
        description = "Descrição", 
        responses = {
            @ApiResponse(description = "A lista objetos foi retornada", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))
            })
        },
        parameters = {
            @Parameter(name = "size", description = "quantidade de itens", schema = @Schema(type = "integer")),
            @Parameter(name = "page", description = "página desejada", schema = @Schema(type = "integer")),
            @Parameter(name = "order", description = "ordenação ASC ou DESC", schema = @Schema(type = "string")),
        }
    )
    public ResponseEntity<List<TModel>> onGetMethod(
            @RequestParam int size, 
            @RequestParam int page, 
            @RequestParam(defaultValue = "ASC") String order) {
        var sortDirection = order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        var data = service.findAll(PageRequest.of(page, size, sortDirection, "id"));
        return ResponseEntity.ok(data.getContent());
    }

}
