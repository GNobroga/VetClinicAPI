package com.veterinary.care.api.insfrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.care.api.domain.entities.ClientEntity;
import com.veterinary.care.api.domain.projection.ClientProjection;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {

    @Query("select distinct c from ClientEntity c join fetch c.person left join c.dogs")
    Page<ClientProjection> getAllProjections(Pageable pageable);

    @Query("select distinct c from ClientEntity c join fetch c.person left join c.dogs where c.id = :id")
    Optional<ClientProjection> getProjectionById(@Param("id") Long id);
}
