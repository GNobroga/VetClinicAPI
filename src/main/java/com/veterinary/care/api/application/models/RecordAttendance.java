package com.veterinary.care.api.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecordAttendance(
        @NotBlank(message = "A data de atendimento não pode ser vazio.")
        @Schema(description = "Data de atendimento no formato: YYYY-MM-DD")
        String attendanceDate,

        String diagnosis,

        String comments,

        Float dogWeight,

        Float dogHeight,

        String dogTemperament,

        @NotNull(message = "O dogId não pode ser nulo")
        Long dogId,

        @NotNull(message = "O clientId não pode ser nulo")
        Long clientId,

        @NotNull(message = "O veterinaryId não pode ser nulo")
        Long veterinaryId) {

}
