package com.veterinary.care.api.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.care.api.application.interfaces.ClientService;
import com.veterinary.care.api.application.models.RecordClient;
import com.veterinary.care.api.application.utils.ResponseHandler;
import com.veterinary.care.api.application.utils.ResponseHandler.Status;
import com.veterinary.care.api.domain.entities.ClientEntity;
import com.veterinary.care.api.domain.projection.ClientProjection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Client", description = "CRUD para clientes")
@RequestMapping("/api/v1/clientes")
@RestController
public class ClientController extends GenericController<ClientEntity, RecordClient, ClientProjection, ClientService> {

    @Override
    @PutMapping("/pessoa/desassociar")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Permite remover o tipo cliente de uma pessoa",
        description = "Descrição",
        responses = {
            @ApiResponse(description = "Retorna a entidade atualizada", responseCode = "200", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"200\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Objeto de resposta", responseCode = "400", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":null}"))
            })
        }
    )
    public ResponseHandler<ClientProjection> onPutMethod(
        @Parameter(name = "id", description =  "id da entidade a ser atualizada", hidden = true)
        @PathVariable(required = false) Long id,
        @RequestBody @Valid RecordClient model
    ) {
        return new ResponseHandler<>(Status.OK, HttpStatus.NO_CONTENT, service.update(id, model));
    }

    @PostMapping("/associar")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(
        summary = "Permite associar o tipo cliente a uma pessoa",
        description = "Descrição",
        responses = {
            @ApiResponse(description = "Objeto de resposta", responseCode = "201", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"201\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Objeto de resposta", responseCode = "400", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":null}"))
            })
        }
    )
    public ResponseHandler<ClientProjection> onPostMethod(
        @RequestBody @Valid RecordClient model
    ) {
        return new ResponseHandler<>(Status.OK, HttpStatus.CREATED, service.create(model));
    }


    @Override
    @DeleteMapping("/pessoa/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Permite remover uma pessoa que tenha o papel cliente",
        description = "Descrição",
        responses = {
            @ApiResponse(description = "Retorna a entidade atualizada", responseCode = "204", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"ok\",\"code\":\"200\",\"messages\":[],\"result\":[]}"))
            }),
            @ApiResponse(description = "Objeto de resposta", responseCode = "400", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "JSON", example = "{\"status\":\"error\",\"code\":\"400\",\"messages\":[],\"result\":null}"))
            })
        }
    )
    public ResponseHandler<?> onDeleteMethod(
        @Parameter(name = "id", description =  "id da pessoa entidade a ser deletada", hidden = false)
        @PathVariable Long id
    ) {
        service.delete(id);
        return new ResponseHandler<>(Status.NO_CONTENT, HttpStatus.NO_CONTENT, null);
    }
}
