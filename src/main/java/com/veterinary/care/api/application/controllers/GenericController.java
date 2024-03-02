package com.veterinary.care.api.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"200\",\"messages\":[],\"result\":[]}"))
            })
        }
    )
    public ResponseHandler<List<P>> onGetMethod(
            @Parameter(name = "pageNumber", description = "página desejada", schema = @Schema(type = "integer", defaultValue = "0"))
            @RequestParam(defaultValue = "0")
                int pageNumber,
            @Parameter(name = "pageSize", description = "quantidade de itens (limite é 50)", schema = @Schema(type = "integer", defaultValue = "10"))
            @RequestParam(defaultValue = "10")
                int pageSize,
            @Parameter(name = "order", description = "ordenação ASC ou DESC", schema = @Schema(type = "string", defaultValue = "ASC"))
            @RequestParam(defaultValue = "ASC")
                String order) {

        pageNumber = pageNumber < 0 ? 0 : pageNumber;
        pageSize = pageSize > 50 ? 50 : pageSize;

        var sortDirection = order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        var data = service.findAll(PageRequest.of(pageNumber, pageSize, sortDirection, "id"));
        return new ResponseHandler<List<P>>(data);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(
        summary = "Permite criar um objeto dessa entidade",
        description = "Descrição",
        responses = {
            @ApiResponse(description = "Objeto de resposta", responseCode = "201", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"201\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Objeto de resposta", responseCode = "400", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":[]}"))
            })
        }
    )
    public ResponseHandler<P> onPostMethod(
        @RequestBody @Valid M model
    ) {
        return new ResponseHandler<>(Status.OK, HttpStatus.CREATED, service.create(model));
    }


    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(
        summary = "Permite atualizar um objeto dessa entidade",
        description = "Descrição",
        responses = {
            @ApiResponse(description = "Retorna a entidade atualizada", responseCode = "200", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"200\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Objeto de resposta", responseCode = "400", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":[]}"))
            })
        }
    )
    public ResponseHandler<P> onPutMethod(
        @Parameter(name = "id", description =  "id da entidade a ser atualizada")
        @PathVariable Long id,
        @RequestBody @Valid M model
    ) {
        return new ResponseHandler<>(Status.OK, HttpStatus.OK, service.update(id, model));
    }


}
