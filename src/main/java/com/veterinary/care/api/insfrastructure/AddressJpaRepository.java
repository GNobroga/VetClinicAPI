package com.veterinary.care.api.insfrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.care.api.domain.entities.AddressEntity;
import com.veterinary.care.api.domain.projection.AddressProjection;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    @Query("from AddressEntity ae join fetch ae.person")
    Page<AddressProjection> findAllWithProjection(Pageable pageable);

    @Query("from AddressEntity ae join fetch ae.person where ae.id = :id")
    Optional<AddressProjection> getProjectionById(@Param("id") Long id);

}
