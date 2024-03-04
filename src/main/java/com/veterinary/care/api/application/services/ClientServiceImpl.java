package com.veterinary.care.api.application.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.enums.PersonType;
import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.interfaces.ClientService;
import com.veterinary.care.api.application.mappers.ClientMapper;
import com.veterinary.care.api.application.models.RecordClient;
import com.veterinary.care.api.domain.entities.ClientEntity;
import com.veterinary.care.api.domain.projection.ClientProjection;
import com.veterinary.care.api.insfrastructure.ClientJpaRepository;
import com.veterinary.care.api.insfrastructure.PersonJpaRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientJpaRepository repository;

    @Autowired
    private PersonJpaRepository personJpaRepository;

    private ClientMapper mapper = ClientMapper.INSTANCE;

    @Override
    public List<ClientProjection> findAll(PageRequest pageRequest) {
       return repository.getAllProjections(pageRequest).getContent();
    }

    @Override
    public ClientProjection findById(Long id) {
       if (id == null)
            throw new NegociationException("Identificação inválida");

        return repository.getProjectionById(id)
            .orElseThrow(() -> new NegociationException("Cliente não encontrado"));
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public ClientProjection create(RecordClient model) {

        var person = personJpaRepository.findById(model.personId())
            .orElseThrow(() -> new NegociationException("Pessoa não existe"));

        var personType = person.getType();

        if (personType != null) {
            if (personType.equals(PersonType.VETERINARY))
                throw new NegociationException("Um veterinário não pode ser cliente");
            else
                throw new NegociationException("Essa pessoa já é um cliente");
        }

        // Adicionar o type CLIENT na pessoa
        person.setType(PersonType.CLIENT);
        var client = mapper.toEntity(model);
        person.setClient(client);
        client.setPerson(person);
       return repository.getProjectionById(repository.saveAndFlush(client).getId()).get();
    }


    @Transactional
    @SuppressWarnings("null")
    @Override
    public ClientProjection update(Long id, RecordClient model) {
        var personId = model.personId();

        var entity = repository.findById(id)
            .orElseThrow(() -> new NegociationException("Cliente não encontrado"));

        if (entity.getId() != personId)
            throw new NegociationException("A pessoa associada a esse cliente é diferente da que foi especificada");

        personJpaRepository.findById(personId)
            .orElseThrow(() -> new NegociationException("A pessoa não foi encontrada"));

        mapper.toEntity(entity, model);

        repository.saveAndFlush(entity);

        return repository.getProjectionById(id).get();
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NegociationException("Cliente não identificado");
        var client = repository.findById(id)
            .orElseThrow(() -> new NegociationException("Cliente não encontrado"));
        repository.deleteById(id);
        personJpaRepository.deleteById(client.getPerson().getId());
    }


}
