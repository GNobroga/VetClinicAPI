package com.veterinary.care.api.application.models;

import com.veterinary.care.api.application.enums.AddressType;
import com.veterinary.care.api.application.validators.constraints.CheckEnumType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
    name = "Endereço",
    description = "Exemplo de um payload para cadastrar Endereço",
    example = "{ \"place\": \"São Joaquim\", \"number\": \"100\", \"complement\": \"Atrás da academia\", \"zipCode\": \"2936000\", \"type\": \"HOME\" }"
)
@CheckEnumType(className = RecordAddress.class)
public record RecordAddress(
        @Size(max = 255, message = "O place só pode ter no máximo 255 caracteres.")
        @NotBlank(message = "O place é obrigatório.")
        String place,

        @Size(max = 15, message = "O number só pode ter no máximo 15 caracteres.")
        @NotBlank(message = "O number é obrigatório.")
        String number,

        @Size(max = 255, message = "O complemento pode ter no máximo 255 caracteres.")
        String complement,

        @Size(max = 255, message = "O zipCode só pode ter no máximo 255 caracteres.")
        @NotBlank(message = "O zipCode é obrigatório.")
        String zipCode,

        @CheckEnumType.EnumType(AddressType.class)
        String type
    ) {
}
