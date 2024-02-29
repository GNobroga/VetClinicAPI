package com.veterinary.care.api.application.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Cliente", type = "Object")
public record RecordClient(

    @NotBlank(message = "A data registro não pode ser vazio.")
    @Schema(description = "Data de registro no formato: YYYY-MM-DD")
    String registrationDate,

    @NotNull(message = "O personId não pode ser vazio.")
    Long personId
) {
    
}
