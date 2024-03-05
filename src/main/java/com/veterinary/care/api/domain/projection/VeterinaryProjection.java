package com.veterinary.care.api.domain.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "document",
        "birthDate",
        "username",
        "crmv",
        "crmvUf",
        "phone",
        "email",
        "specialty",
        "addresses",
        "attendances"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface VeterinaryProjection {

    Long getId();

    @Value("#{target.person.name}")
    String getName();

    LocalDateTime getRegistrationDate();

    @Value("#{target.person.document}")
    String getDocument();

    @Value("#{target.person.birthDate}")
    LocalDate getBirthDate();

    @Value("#{target.person.phone}")
    String getPhone();

    @Value("#{target.person.email}")
    String getEmail();

    @Value("#{target.person.user.username}")
    String getUsername();

    String getSpecialty();

    String getCrmv();

    String getCrmvUf();

    @Value("#{target.person.addresses}")
    List<AddressProjection> getAddresses();

    @Value("#{target.attendances}")
    List<AttedanceProjection> getAttendances();

    interface AttedanceProjection {
        Long getId();

        LocalDateTime getAttendanceDate();

        String getDiagnosis();

        String getComments();

        Double getDogWeight();

        Double getDogHeight();

        String getDogTemperament();

        BigDecimal getPrice();

        @Value("#{target.dog}")
        DogProjection getDog();
    }

    interface AddressProjection {
        Long getId();

        String getPlace();

        String getNumber();

        String getComplement();

        String getZipCode();

        String getType();
    }

    interface DogProjection {
        Long getId();

        String getBreed();

        LocalDate getBirthDate();

        LocalDateTime getRegistrationDate();
    }
}
