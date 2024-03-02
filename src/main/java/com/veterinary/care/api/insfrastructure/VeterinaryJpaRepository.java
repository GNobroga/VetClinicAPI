package com.veterinary.care.api.insfrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.VeterinaryEntity;

public interface VeterinaryJpaRepository extends JpaRepository<VeterinaryEntity, Long> {

}
