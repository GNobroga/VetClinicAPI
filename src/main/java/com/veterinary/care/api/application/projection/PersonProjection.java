package com.veterinary.care.api.application.projection;

import java.time.LocalDate;

public interface PersonProjection {
    
    Long getId();

    LocalDate getBirthDate();

    String getType();

    String getDocument();

    String getPhone();

    String getEmail();

    String getUsername();

    LocalDate getRegistrationDate();

}
