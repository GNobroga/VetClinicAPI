package com.veterinary.care.api.insfrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
