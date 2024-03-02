package com.veterinary.care.api.application.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(name = "Cliente", type = "Object")
public record RecordClient(

    @Pattern(regexp = "^(?:\\d{4})-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12][0-9]|3[01])$", message = "A data precisa estar no formato YYYY-MM-DD")
    @NotBlank(message = "A data registro não pode ser vazio.")
    String registrationDate,

    @NotNull(message = "O personId não pode ser vazio.")
    Long personId
) {

}
