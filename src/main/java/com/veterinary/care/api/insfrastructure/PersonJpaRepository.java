package com.veterinary.care.api.insfrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.PersonEntity;
import com.veterinary.care.api.domain.interfaces.PersonRepository;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long>, PersonRepository {

}
