package com.veterinary.care.api.insfrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.care.api.domain.entities.DogEntity;
import com.veterinary.care.api.domain.projection.DogProjection;

public interface DogJpaRepository extends JpaRepository<DogEntity, Long> {

    @Query("from DogEntity de join fetch de.client left join fetch attendances")
    Page<DogProjection> getAllProjections(Pageable pageable);

    @Query("from DogEntity de join fetch de.client left join fetch attendances where de.id = :id")
    Optional<DogProjection> getProjectionById(@Param("id") Long id);
}
