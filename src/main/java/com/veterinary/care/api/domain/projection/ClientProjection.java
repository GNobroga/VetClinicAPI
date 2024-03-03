package com.veterinary.care.api.domain.projection;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface ClientProjection {

    Long getId();
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

    @Value("#{target.dogs}")
    List<DogProjection> getDogs();

    interface DogProjection {
        Long getId();
        String getBreed();
        LocalDate getBirthDate();
        LocalDate getRegistrationDate();
    }
}
