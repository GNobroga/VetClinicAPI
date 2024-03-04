package com.veterinary.care.api.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * Uma classe base com o padrão de métodos para controllers
 *
 * @param <E> Uma entidade que extende de BaseEntity
 * @param <M> Um objeto de input
 * @param <S> Um service que extende de GenericService
 */
@Validated
public class GenericController<E extends BaseEntity, M, P, S extends GenericService<E, M, P>> {

    @Autowired
    protected S service;

    @GetMapping
    @Operation(summary = "Retorna lista de objetos dessa entidade", description = "Descrição", responses = {
            @ApiResponse(description = "Retorna o objeto de resposta", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"200\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Retorna o objeto de resposta", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":null}"))
            })
    })
    public ResponseHandler<List<P>> onGetMethod(
            @Parameter(name = "pageNumber", description = "página desejada", schema = @Schema(type = "integer", defaultValue = "0")) @RequestParam(defaultValue = "0") int pageNumber,
            @Parameter(name = "pageSize", description = "quantidade de itens (limite é 50)", schema = @Schema(type = "integer", defaultValue = "10")) @RequestParam(defaultValue = "10") int pageSize,
            @Parameter(name = "order", description = "ordenação ASC ou DESC", schema = @Schema(type = "string", defaultValue = "ASC")) @RequestParam(defaultValue = "ASC") String order) {

        pageNumber = pageNumber < 0 ? 0 : pageNumber;
        pageSize = pageSize > 50 ? 50 : pageSize;

        order = order == null || (!order.equalsIgnoreCase("asc") || !order.equalsIgnoreCase("desc")) ? "ASC" : "DESC";

        var sortDirection = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        var data = service.findAll(PageRequest.of(pageNumber, pageSize, sortDirection, "id"));
        return new ResponseHandler<List<P>>(data);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um objeto dessa entidade", description = "Descrição", responses = {
            @ApiResponse(description = "Retorna o objeto de resposta", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"200\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Retorna o objeto de resposta", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":null}"))
            })
    })
    public ResponseHandler<P> onGetMethod(@PathVariable Long id) {
        return new ResponseHandler<P>(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Permite criar um objeto dessa entidade", description = "Descrição", responses = {
            @ApiResponse(description = "Objeto de resposta", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"201\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Objeto de resposta", responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":null}"))
            })
    })
    public ResponseHandler<P> onPostMethod(
            @RequestBody @Valid M model) {
        return new ResponseHandler<>(Status.OK, HttpStatus.CREATED, service.create(model));
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Permite atualizar um objeto dessa entidade", description = "Descrição", responses = {
            @ApiResponse(description = "Retorna a entidade atualizada", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"200\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Objeto de resposta", responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":null}"))
            })
    })
    public ResponseHandler<P> onPutMethod(
            @Parameter(name = "id", description = "id da entidade a ser atualizada") @PathVariable Long id,
            @RequestBody @Valid M model) {
        return new ResponseHandler<>(Status.OK, HttpStatus.OK, service.update(id, model));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Permite atualizar um objeto dessa entidade", description = "Descrição", responses = {
            @ApiResponse(description = "Retorna a entidade atualizada", responseCode = "204", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"200\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Objeto de resposta", responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":null}"))
            })
    })
    public ResponseHandler<?> onDeleteMethod(
            @Parameter(name = "id", description = "id da entidade a ser deletada") @PathVariable Long id) {
        service.delete(id);
        return new ResponseHandler<>(Status.NO_CONTENT, HttpStatus.NO_CONTENT, null);
    }

}
