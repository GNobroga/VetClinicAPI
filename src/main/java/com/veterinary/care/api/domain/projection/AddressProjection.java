package com.veterinary.care.api.domain.projection;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id"})
public interface AddressProjection {

    Long getId();

    String getPlace();

    String getNumber();

    String getComplement();

    String getType();

    @JsonProperty("cep")
    String getZipCode();

    @Value("#{target.person.name}")
    String getName();

    @Value("#{target.person.document}")
    String getDocument();

    @Value("#{target.person.phone}")
    String getPhone();

    @Value("#{target.person.email}")
    String getEmail();


}