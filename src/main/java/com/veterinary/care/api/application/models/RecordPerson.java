package com.veterinary.care.api.application.models;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecordPerson(

        @Size(max = 255, message = "O nome não pode ter mais de 255 caracteres")
        @NotBlank(message = "O nome não pode vazio.")
        String name,

        @NotBlank(message = "O tipo de pessoa não pode ser nulo")
        String type,

        @NotBlank(message = "O documento é obrigatório.")
        String document,

        @NotBlank(message = "A data de nascimento é obrigatório.")
        String birthDate,

        @NotBlank(message = "O telefone é obrigatório.")
        String phone,

        @Email(message = "O email precisa ser válido.")
        String email,

        @Size(max = 100, message = "A username pode ter no máximo 100 caracteres.")
        @NotBlank(message = "O username é obrigatório.")
        String username,

        @Size(max = 100, message = "A senha pode ter no máximo 100 caracteres.")
        @NotBlank(message = "A senha é obrigatório.")
        String password,

        @NotNull(message = "O addresses é obrigatório.")
        @Valid
        List<RecordAddress> addresses
        ) {
}
