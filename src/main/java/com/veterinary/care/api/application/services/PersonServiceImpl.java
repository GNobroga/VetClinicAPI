package com.veterinary.care.api.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.interfaces.PersonService;
import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.domain.projection.PersonProjection;
import com.veterinary.care.api.insfrastructure.repositories.PersonJpaRepository;

@Service
public class PersonServiceImpl implements PersonService  {

    @Autowired
    private PersonJpaRepository repository;

    @Override
    public Page<PersonProjection> findAll(PageRequest pageRequest) {
        return repository.findAllWithProjection(pageRequest);
    }

    @Override
    public PersonProjection findById(Long id) {
        return null;
    }

    @Override
    public PersonProjection create(RecordPerson model) {
        return null;
    }

    @Override
    public PersonProjection update(Long id, RecordPerson model) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    
}
