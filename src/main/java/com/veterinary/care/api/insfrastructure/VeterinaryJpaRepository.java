package com.veterinary.care.api.insfrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.care.api.domain.entities.VeterinaryEntity;
import com.veterinary.care.api.domain.projection.VeterinaryProjection;


public interface VeterinaryJpaRepository extends JpaRepository<VeterinaryEntity, Long> {

    @Query("from VeterinaryEntity ve join fetch person left join attendances")
    Page<VeterinaryProjection> getAllProjections(Pageable pageable);

    @Query("from VeterinaryEntity ve join fetch person left join attendances where ve.id = :id")
    Optional<VeterinaryProjection> getProjectionById(@Param("id") Long id);

    Optional<VeterinaryEntity> findByCrmv(String crmv);
}
