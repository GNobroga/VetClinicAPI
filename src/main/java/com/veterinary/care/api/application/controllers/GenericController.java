package com.veterinary.care.api.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.veterinary.care.api.application.interfaces.GenericService;
import com.veterinary.care.api.application.utils.ResponseHandler;
import com.veterinary.care.api.application.utils.ResponseHandler.Status;
import com.veterinary.care.api.domain.entities.base.BaseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/**
 * E = Entity
 * M = Model
 * P = Projection
 * S = Service
 */
@Validated
public class GenericController<E extends BaseEntity, M, P, S extends GenericService<E, M, P>> {

    @Autowired
    private S service;

    @GetMapping
    @Operation(
        summary = "Retorna lista de objetos dessa entidade", 
        description = "Descrição", 
        responses = {
            @ApiResponse(description = "A lista objetos foi retornada", responseCode = "200", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", 
                example = """ 
                    { 
                        "status": "ok",
                        "code": "200",
                        "messages": [],
                        "result": []
                    }
                    """
                ))
            })
        }
    )
    public ResponseHandler<List<P>> onGetMethod(
            @Parameter(name = "size", description = "página desejada", schema = @Schema(type = "integer", defaultValue = "0"))
            @RequestParam(defaultValue = "0") 
                int pageNumber, 
            @Parameter(name = "page", description = "quantidade de itens (limite é 50)", schema = @Schema(type = "integer", defaultValue = "10"))
            @RequestParam(defaultValue = "10") 
                int pageSize, 
            @Parameter(name = "order", description = "ordenação ASC ou DESC", schema = @Schema(type = "string", defaultValue = "ASC"))
            @RequestParam(defaultValue = "ASC") 
                String order) {
        
        pageNumber = pageNumber < 0 ? 0 : pageNumber;
        pageSize = pageSize > 50 ? 50 : pageSize;
            
        var sortDirection = order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        var data = service.findAll(PageRequest.of(pageNumber, pageSize, sortDirection, "id"));
        return new ResponseHandler<List<P>>(data.getContent());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(
        summary = "Permite criar um objeto dessa entidade", 
        description = "Descrição", 
        responses = {
            @ApiResponse(description = "Retorna a entidade criada", responseCode = "201", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", 
                example = """
                    {
                        "status": "ok",
                        "code": "201",
                        "messages": [],
                        "result": []
                    }
                    """
                ))
            })
        }
    )
    public ResponseHandler<P> onPostMethod(
        @RequestBody @Valid M model
    ) {
        System.out.println(model);
        return new ResponseHandler<>(Status.OK, HttpStatus.CREATED, service.create(model));
    }

}
