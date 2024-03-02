package com.veterinary.care.api.application.models;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

        @NotNull(message = "O dogId não pode ser nulo")
        Long dogId,

        @NotNull(message = "O clientId não pode ser nulo")
        Long clientId,

        @NotNull(message = "O veterinaryId não pode ser nulo")
        Long veterinaryId) {

}
