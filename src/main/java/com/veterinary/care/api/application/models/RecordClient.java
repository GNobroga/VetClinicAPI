package com.veterinary.care.api.application.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Cliente", type = "Object", example = "{ \"personId\": 1 }")
public record RecordClient(

    @Size(max = 100, message = "A comunicação preferida pode ter no máximo 45 caracteres")
    String communicationPreferences,

    @Size(max = 45, message = "O telefone alternativo pode ter no máximo 45 caracteres")
    String alternatePhone,
    @NotNull(message = "O personId é obrigatório")
    Long personId
) {

}
