package com.veterinary.care.api.domain.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "breed", "birthDate", "registrationDate"})
public interface DogProjection {

    Long getId();
    String getName();
    String getBreed();
    LocalDateTime getRegistrationDate();
    LocalDate getBirthDate();

}
