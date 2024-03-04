package com.veterinary.care.api.application.models;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(
    name = "Atendimento",
    description = "Payload para Atendimento",
    example = "{\"attendanceDate\":\"2022-03-03\",\"diagnosis\":\"Some diagnosis\",\"comments\":\"Some comments\",\"dogWeight\":25.5,\"dogHeight\":60.0,\"dogTemperament\":\"Friendly\",\"price\":150.0,\"dogId\":123,\"clientId\":456,\"veterinaryId\":789}"
)
public record RecordAttendance(

        @Pattern(regexp = "^(?:\\d{4})-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12][0-9]|3[01])$", message = "A data precisa estar no formato YYYY-MM-DD")
        @NotNull(message = "A data do atendimento é obrigatório")
        String attendanceDate,

        String diagnosis,

        String comments,

        @DecimalMax(value = "100000", inclusive = true, message = "O peso do cachorro não pode ser maior que 100000")
        @DecimalMin(value = "0", inclusive = true,  message = "O peso do cachorro não pode ser menor que 0")
        Double dogWeight,

        @DecimalMax(value = "100000", inclusive = true, message = "O altura do cachorro não pode ser maior que 100000")
        @DecimalMin(value = "0", inclusive = true,  message = "O altura do cachorro não pode ser menor que 0")
        Double dogHeight,

        String dogTemperament,

        @DecimalMax(value = "100000", inclusive = true, message = "O preço não pode ser maior que 100000")
        @DecimalMin(value = "0", inclusive = true,  message = "O preço não pode ser menor que 0")
        @NotNull(message = "O preço é obrigatório")
        BigDecimal price,

        @NotNull(message = "O dogId é obrigatório")
        Long dogId,

        @NotNull(message = "O clientId é obrigatório")
        Long clientId,

        @NotNull(message = "O veterinaryId é obrigatório")
        Long veterinaryId) {

}
