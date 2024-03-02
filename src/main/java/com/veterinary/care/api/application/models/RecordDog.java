package com.veterinary.care.api.application.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RecordDog(
        @Size(max = 255, message = "O name só pode ter no máximo 255 caracteres")
        @NotBlank(message = "O name não pode ser vazio.")
        String name,

        @Size(max = 255, message = "O name só pode ter no máximo 255 caracteres")
        String breed,

        @Pattern(regexp = "^(?:\\d{4})-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12][0-9]|3[01])$", message = "A data precisa estar no formato YYYY-MM-DD")
        @NotBlank(message = "A data de nascimento é obrigatório")
        String birthDate,

        @NotBlank(message = "A data de registro não pode ser vazio.")
        @Pattern(regexp = "^(?:\\d{4})-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12][0-9]|3[01])$", message = "A data precisa estar no formato YYYY-MM-DD")
        String registrationDate,

        @NotNull(message = "O clientId não pode ser vazio.")
        Long clientId
) {

}
