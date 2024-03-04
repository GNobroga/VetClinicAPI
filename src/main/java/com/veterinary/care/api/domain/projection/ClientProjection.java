package com.veterinary.care.api.domain.projection;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "document", "email", "username", "phone", "birthDate", "communicationPreferences", "alternatePhone","registrationDate"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ClientProjection {

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

    interface DogProjection {
        Long getId();
        String getBreed();
        LocalDate getBirthDate();
        LocalDate getRegistrationDate();
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
