package com.veterinary.care.api.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.veterinary.care.api.application.enums.AddressType;
import com.veterinary.care.api.application.validators.constraints.CheckEnumType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
    name = "Endereço Cadastro",
    description = "Exemplo de um payload para cadastrar Endereço",
    example = "{\"place\": \"São Joaquim\", \"number\": \"100\", \"complement\": \"Atrás da academia\", \"cep\": \"29360-000\", \"type\": \"HOME\", \"personId\": 1}"
)
@CheckEnumType(className = RecordAddressWithPerson.class)
public record RecordAddressWithPerson(
        @Size(max = 255, message = "O place só pode ter no máximo 255 caracteres")
        @NotBlank(message = "O place é obrigatório")
        String place,

        @Size(max = 15, message = "O number só pode ter no máximo 15 caracteres")
        @NotBlank(message = "O number é obrigatório")
        String number,

        @Size(max = 255, message = "O complemento pode ter no máximo 255 caracteres")
        String complement,

        @JsonProperty("cep")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "O cep não possui um valor válido")
        @NotBlank(message = "O cep é obrigatório")
        String zipCode,

        @CheckEnumType.EnumType(AddressType.class)
        @NotNull(message = "O type é obrigatório")
        String type,

        @NotNull(message = "O personId é obrigatório")
        Long personId
    ) {
}
