package com.veterinary.care.api.application.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Cliente", type = "Object", example = "{ \"personId\": 1 }")
public record RecordClient(

    @NotNull(message = "O personId é obrigatório")
    Long personId
) {

}
