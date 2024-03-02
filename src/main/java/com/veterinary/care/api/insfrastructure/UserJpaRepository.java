package com.veterinary.care.api.insfrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

}
