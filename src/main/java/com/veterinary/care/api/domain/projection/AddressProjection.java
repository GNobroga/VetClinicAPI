package com.veterinary.care.api.domain.projection;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface AddressProjection {

    Long getId();

    String getPublicPlace();

    String getNumber();

    String getComplement();

    String getAddressType();

    @JsonProperty("personId")
    @Value("#{target.person.id}")
    Long getPersonId();

    // interface PersonProjection {
    //     Long getId();
    //     String getName();
    //     String get

    // }
}