package com.veterinary.care.api.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.veterinary.care.api.application.interfaces.GenericService;
import com.veterinary.care.api.application.utils.ResponseHandler;
import com.veterinary.care.api.domain.entities.base.BaseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public class GenericController<TEntity extends BaseEntity, TModel, TProjection, TService extends GenericService<TEntity, TModel, TProjection>> {

    @Autowired
    private TService service;

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
    public ResponseHandler<List<TProjection>> onGetMethod(
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

        System.out.println("Pagina " + pageSize);
            
        var sortDirection = order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        var data = service.findAll(PageRequest.of(pageNumber, pageSize, sortDirection, "id"));
        return new ResponseHandler<List<TProjection>>(data.getContent());
    }

}
