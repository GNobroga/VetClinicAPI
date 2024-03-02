package com.veterinary.care.api.insfrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.RoleEntity;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

}
