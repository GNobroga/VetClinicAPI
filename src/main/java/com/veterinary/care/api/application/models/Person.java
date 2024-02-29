package com.veterinary.care.api.application.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.veterinary.care.api.application.enums.PersonType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public record Person(
    Long id,
    String name,
    String type,
    String document,
    String birthDate,
    String phone,
    String email,
    User user,
    
) {}


public class Person {

    private Long id;
    
    private String name;
  
    private PersonType type;

    private String document;

    private LocalDate birthDate;
    
    private String phone;

    private String email;

    private UserEntity user;

    private ClientEntity client;

    private List<AddressEntity> addresses = new ArrayList<>();
}
