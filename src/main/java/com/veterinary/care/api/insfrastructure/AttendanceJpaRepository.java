package com.veterinary.care.api.insfrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.care.api.domain.entities.AttendanceEntity;
import com.veterinary.care.api.domain.projection.AttendanceProjection;

public interface AttendanceJpaRepository extends JpaRepository<AttendanceEntity, Long> {

    @Query("from AttendanceEntity ae join fetch ae.dog join fetch ae.client join fetch ae.veterinary")
    Page<AttendanceProjection> getAllByProjection(Pageable pageable);


    @Query("from AttendanceEntity ae join fetch ae.dog join fetch ae.client join fetch ae.veterinary where ae.id = :id")
    Optional<AttendanceProjection> getByProjectionId(@Param("id") Long id);
}
