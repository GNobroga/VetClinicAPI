package com.veterinary.care.api.domain.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "diagnosis", "comments", "price", "attendanceDate", "dogWeigth", "dogHeight", "dogTemperament"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface AttendanceProjection {

    Long getId();

    String getDiagnosis();

    LocalDateTime getAttendanceDate();

    String getComments();

    String getDogWeight();

    String getDogHeight();

    String getDogTemperament();

    BigDecimal getPrice();

    DogProjection getDog();

    @Value("#{target.client}")
    ClientProjection getClient();

    @Value("#{target.veterinary}")
    VeterinaryProjection getVeterinary();

    interface ClientProjection {
        Long getId();

        @Value("#{target.person.name}")
        String getName();

        LocalDateTime getRegistrationDate();

        String getCommunicationPreferences();

        String getAlternatePhone();

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

        @Value("#{target.person.addresses}")
        List<AddressProjection> getAddresses();
    }

    interface VeterinaryProjection {
        Long getId();

        @Value("#{target.person.name}")
        String getName();

        @Value("#{target.person.document}")
        String getDocument();

        @Value("#{target.person.birthDate}")
        LocalDate getBirthDate();

        LocalDateTime getRegistrationDate();

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
    }

    interface AddressProjection {
        Long getId();
        String getPlace();
        String getNumber();
        String getComplement();
        String getZipCode();
        String getType();
    }

}
