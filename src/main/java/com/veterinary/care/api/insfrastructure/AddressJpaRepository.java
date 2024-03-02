package com.veterinary.care.api.insfrastructure;

import java.util.List;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veterinary.care.api.domain.entities.AddressEntity;
import com.veterinary.care.api.domain.projection.AddressProjection;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    @Query("from AddressEntity ae join fetch ae.person")
    List<AddressProjection> findAllWithProjection(Pageable pageable);
}
