package com.veterinary.care.api.application.models;

import jakarta.validation.constraints.NotBlank;

public record RecordUser(
        @NotBlank(message = "O username é obrigatório.")
        String username,

        @NotBlank(message = "A senha é obrigatório.")
        String password) {
}
