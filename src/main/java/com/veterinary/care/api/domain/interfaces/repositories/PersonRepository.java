package com.veterinary.care.api.domain.interfaces.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.care.api.domain.entities.PersonEntity;

public interface PersonRepository {
    
    @Query("from Person p where p.email = :email")
    PersonEntity findByEmail(@Param("email") String email);

    @Query("from Person p join fetch p.user u where u.username = :username")
    PersonEntity findByUsername(@Param("username") String username);

}
