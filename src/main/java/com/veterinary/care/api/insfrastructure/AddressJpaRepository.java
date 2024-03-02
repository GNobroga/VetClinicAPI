package com.veterinary.care.api.insfrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.AddressEntity;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

}
