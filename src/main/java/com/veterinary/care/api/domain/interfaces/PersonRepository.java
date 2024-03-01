package com.veterinary.care.api.domain.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


import com.veterinary.care.api.domain.projection.PersonProjection;

public interface PersonRepository {

    @Query("""
    SELECT 
        p
        FROM PersonEntity p
        JOIN FETCH p.user u
        JOIN FETCH p.addresses a
    """)
    Page<PersonProjection> findAllWithProjection(Pageable pageable);
    


}
