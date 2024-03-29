package com.veterinary.care.api.application.models;

import java.util.List;

import com.veterinary.care.api.application.validators.constraints.CheckDocument;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Pessoa",
        description = "Payload para Pessoa",
        example = "{\"name\": \"Gabriel\", \"birthDate\": \"2000-04-10\", \"document\": \"17364509720\", \"phone\": \"28999505410\", \"email\": \"gabrielcardoso123@gmail.com\", \"username\": \"gabiroba\", \"password\": \"gabiroba123\", \"addresses\": [{\"place\": \"Elizeu meia nove\", \"number\": \"100\", \"complement\": \"atrás do seu josé\", \"cep\": \"29360000\", \"type\": \"WORK\"}]}"
)
public record RecordPerson(

        @Size(min = 2, max = 255, message = "O nome não pode ter mais de 255 caracteres e deve ter no mínimo 2 caracteres")
        @NotBlank(message = "O nome não pode vazio")
        String name,

        @CheckDocument
        @NotBlank(message = "O documento é obrigatório")
        String document,

        @Pattern(regexp = "^(?:\\d{4})-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12][0-9]|3[01])$", message = "A data precisa estar no formato YYYY-MM-DD")
        @NotBlank(message = "A data de nascimento é obrigatório")
        String birthDate,

        @Size(min = 8, max = 255, message = "O nome não pode ter mais de 45 caracteres e deve ter no mínimo 8 caracteres")
        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @Email(message = "O email precisa ser válido")
        String email,

        @Size(max = 100, message = "A username pode ter no máximo 100 caracteres")
        @NotBlank(message = "O username é obrigatório")
        String username,

        @Size(max = 100, message = "A senha pode ter no máximo 100 caracteres")
        @NotBlank(message = "A senha é obrigatório")
        String password,

        @NotNull(message = "O addresses é obrigatório")
        @Valid
        List<RecordAddress> addresses
        ) {
}
