package com.veterinary.care.api.domain.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.care.api.domain.entities.PersonEntity;
import com.veterinary.care.api.domain.projection.PersonProjection;

public interface PersonRepository {

    @Query("from PersonEntity p join fetch p.user join fetch p.addresses")
    Page<PersonProjection> findAllWithProjection(Pageable pageable);

    @Query("from PersonEntity p join fetch p.user u join fetch p.addresses a where p.id = :id")
    PersonProjection findByIdWithProjection(@Param("id") Long id);

    @Query("from PersonEntity p join fetch p.user u where p.email = :email or u.username = :username")
    Optional<PersonEntity> findByEmailOrUsername(@Param("email") String email, @Param("username") String username);

}
