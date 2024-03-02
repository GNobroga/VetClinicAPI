package com.veterinary.care.api.insfrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.care.api.domain.entities.PersonEntity;
import com.veterinary.care.api.domain.projection.PersonProjection;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {

    @Query("select distinct p from PersonEntity p join fetch p.user left join fetch p.addresses")
    Page<PersonProjection> findAllWithProjection(Pageable pageable);

    @Query("select distinct p from PersonEntity p join fetch p.user u left join fetch p.addresses a where p.id = :id")
    PersonProjection getProjectionById(@Param("id") Long id);

    @Query("from PersonEntity p join fetch p.user u where u.username = :username")
    Optional<PersonEntity> findByUsername(@Param("username") String username);

    Optional<PersonEntity> findByEmail(String email);

    @Query("from PersonEntity p join fetch p.user u where p.email = :email or u.username = :username")
    List<PersonEntity> findByEmailOrUsername(@Param("email") String email, @Param("username") String username);

    @Query("from PersonEntity p where p.document = :document")
    Optional<PersonEntity> findByDocument(@Param("document") String document);
}
