package com.veterinary.care.api.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.interfaces.PersonService;
import com.veterinary.care.api.application.mappers.PersonMapper;
import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.domain.projection.PersonProjection;
import com.veterinary.care.api.insfrastructure.repositories.PersonJpaRepository;

import jakarta.persistence.EntityManager;

@Service
public class PersonServiceImpl implements PersonService  {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PersonJpaRepository repository;

    private PersonMapper mapper = PersonMapper.INSTANCE;

    @Override
    public List<PersonProjection> findAll(PageRequest pageRequest) {
        return repository.findAllWithProjection(pageRequest).getContent();
    }

    @Override
    public PersonProjection findById(Long id) {
        return repository.findByIdWithProjection(id);
    }

    @Override
    public PersonProjection create(RecordPerson model) {

        if (model.addresses().isEmpty())
            throw new NegociationException("A pessoa precisa ter pelo menos 1 endereço cadastrado.");

        var optionalUser = repository.findByEmailOrUsername(model.email(), model.username());

        if (optionalUser.isPresent())
            throw new NegociationException("Email ou usuário já estão cadastrados");

        optionalUser = repository.findByDocument(model.document());

        if (optionalUser.isPresent())
            throw new NegociationException("Documento já cadastrado.");

        var entity = mapper.toEntity(model);
        entity.getAddresses().forEach(x -> x.setPerson(entity));
        return repository.findByIdWithProjection(repository.save(entity).getId());
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
