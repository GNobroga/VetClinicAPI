package com.veterinary.care.api.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecordDog(
        @Size(max = 255, message = "O name só pode ter no máximo 255 caracteres")
        @NotBlank(message = "O name não pode ser vazio.")
        String name,

        @Size(max = 255, message = "O name só pode ter no máximo 255 caracteres")
        String breed,

        String birthDate,

        @NotBlank(message = "A data de registro não pode ser vazio.")
        @Schema(description = "Data precisar estar no formato YYYY-MM-DD")
        String registrationDate,

        @NotNull(message = "O clientId não pode ser vazio.")
        Long clientId
) {

}
