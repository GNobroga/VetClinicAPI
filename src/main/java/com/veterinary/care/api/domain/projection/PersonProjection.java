package com.veterinary.care.api.domain.projection;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","document","type","phone","birthDate","email","username","addresses"})
public interface PersonProjection {

    Long getId();

    LocalDate getBirthDate();

    String getName();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getType();

    String getDocument();

    String getPhone();

    String getEmail();

    @Value("#{target.user.username}")
    String getUsername();

    @Value("#{target.addresses}")
    List<AddressProjection> getAddresses();

    interface AddressProjection {
        Long getId();

        String getPlace();

        String getNumber();

        String getComplement();

        String getZipCode();

        String getType();
    }

}
