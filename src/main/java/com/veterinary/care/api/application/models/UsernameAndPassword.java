package com.veterinary.care.api.application.models;

import jakarta.validation.constraints.NotBlank;

public record UsernameAndPassword (
    @NotBlank(message = "O username é obrigatório")
    String username,

    @NotBlank(message = "A password é obrigatório")
    String password
) {
    
}
