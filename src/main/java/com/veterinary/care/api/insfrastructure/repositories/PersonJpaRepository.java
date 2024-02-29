package com.veterinary.care.api.insfrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.Person;
import com.veterinary.care.api.domain.interfaces.repositories.PersonRepository;

public interface PersonJpaRepository extends JpaRepository<Person, Long>, PersonRepository {
    
}
