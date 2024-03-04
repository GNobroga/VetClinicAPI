package com.veterinary.care.api.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
    name = "Papel",
    description = "Payload para Papel",
    example = "{\"name\":\"Nome do Exemplo\"}"
)
public record RecordRole(

        @Size(max = 255, message = "O nome pode ter no máximo 100 caracteres")
        @NotBlank(message = "O nome é obrigatório")
        String name) {
}