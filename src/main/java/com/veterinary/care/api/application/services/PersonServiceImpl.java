package com.veterinary.care.api.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.interfaces.PersonService;
import com.veterinary.care.api.application.mappers.PersonMapper;
import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.domain.projection.PersonProjection;
import com.veterinary.care.api.insfrastructure.repositories.PersonJpaRepository;

@Service
public class PersonServiceImpl implements PersonService  {

    @Autowired
    private PersonJpaRepository repository;

    private PersonMapper mapper = PersonMapper.INSTANCE;

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

        if (model.addresses().isEmpty()) 
            throw new NegociationException("A pessoa precisa ter pelo menos 1 endereço cadastrado.");

        var optionalUser = repository.findByEmailOrUsername(model.email(), model.username());

        if (optionalUser.isPresent()) 
            throw new NegociationException("Email ou usuário já estão cadastrados");

        var entity = mapper.toEntity(model);

        System.out.println("AQUIIIIIIIIIIIII");
        System.out.println(entity.getAddresses().size());

        entity.getAddresses().forEach(x -> x.setPerson(entity));

        repository.flush();

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
