package com.veterinary.care.api.application.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RecordRole(

        @Size(max = 255, message = "O nome pode ter no máximo 100 caracteres.")
        @NotBlank(message = "O nome é obrigatório.")
        String name) {
}