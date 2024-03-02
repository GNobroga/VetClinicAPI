package com.veterinary.care.api.insfrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.ClientEntity;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {

}
