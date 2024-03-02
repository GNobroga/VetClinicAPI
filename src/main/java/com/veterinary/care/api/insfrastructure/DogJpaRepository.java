package com.veterinary.care.api.insfrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.DogEntity;

public interface DogJpaRepository extends JpaRepository<DogEntity, Long> {

}
