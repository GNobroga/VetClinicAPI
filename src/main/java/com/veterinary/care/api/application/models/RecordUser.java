package com.veterinary.care.api.application.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RecordUser(
        @Size(min = 5, max = 100, message = "O username deve ter no mínimo 5 caracteres e no máximo 100")
        @NotBlank(message = "O username é obrigatório.")
        String username,

        @Size(min = 5, max = 100, message = "A senha deve ter no mínimo 5 caracteres e no máximo 100")
        @NotBlank(message = "A senha é obrigatório.")
        String password) {
}
