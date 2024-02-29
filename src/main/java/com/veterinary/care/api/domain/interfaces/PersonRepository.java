package com.veterinary.care.api.domain.interfaces;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.veterinary.care.api.domain.projection.PersonProjection;

public interface PersonRepository {

    @Query(value = "from PersonEntity p join fetch p.addresses a order by p.name asc")
    Page<PersonProjection> findAllWithProjection(Pageable pageable);
}
