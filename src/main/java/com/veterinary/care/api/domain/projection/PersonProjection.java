package com.veterinary.care.api.domain.projection;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;


public interface PersonProjection {
    
    Long getId();

    LocalDate getBirthDate();

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
