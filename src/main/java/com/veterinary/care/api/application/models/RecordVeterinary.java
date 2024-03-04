package com.veterinary.care.api.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(
    name = "Veterinário",
    description = "Payload para Veterinário",
    example = "{\"specialty\":\"Cardiology\",\"crmv\":\"12345\",\"crmvUf\":\"SP\",\"personId\":1}"
)
public record RecordVeterinary(

        @Size(max = 255, message = "A especialidade pode ter no máximo 255 caracteres")
        @NotBlank(message = "A especialidade é obrigatório")
        String specialty,

        @Size(max = 45, message = "O crmv pode ter no máximo 45 caracteres")
        @NotBlank(message = "O crmv é obrigatório")
        String crmv,

        @Size(max = 2, message = "O crmvUf pode ter no máximo 2 caracteres")
        @NotBlank(message = "O crmv é obrigatório")
        String crmvUf,

        @NotNull(message = "o personId é obrigatório")
        Long personId
        ) {
}
