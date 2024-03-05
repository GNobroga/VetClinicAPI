package com.veterinary.care.api.domain.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder("id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface AttendanceProjection {

    Long getId();

    String getDiagnosis();

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

        String getCommunicationPreferences();

        String getAlternatePhone();

        LocalDate getRegistrationDate();

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

        @Value("#{target.dogs}")
        List<DogProjection> getDogs();
    }

    interface VeterinaryProjection {
        Long getId();

        @Value("#{target.person.name}")
        String getName();

        LocalDate getRegistrationDate();

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
