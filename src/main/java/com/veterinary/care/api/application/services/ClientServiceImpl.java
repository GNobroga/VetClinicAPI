package com.veterinary.care.api.application.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.enums.PersonType;
import com.veterinary.care.api.application.interfaces.ClientService;
import com.veterinary.care.api.application.mappers.ClientMapper;
import com.veterinary.care.api.application.models.RecordClient;
import com.veterinary.care.api.application.utils.CommonValidation;
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
        CommonValidation.throwExceptionIfInvalidId(id, "Cliente");
        return repository.getProjectionById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Cliente"));
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public ClientProjection create(RecordClient model) {

        var person = personJpaRepository.findById(model.personId())
                .orElseThrow(CommonValidation.throwEntityNotfound("Pessoa"));

        if (person.getType() != null)
            CommonValidation.throwBusinessRuleViolation("Esse pesssoa jé tem um tipo associado.");

        // Adicionar o type CLIENT na pessoa
        person.setType(PersonType.CLIENT);
        var client = mapper.toEntity(model);
        person.setClient(client);
        client.setPerson(person);
        client.setRegistrationDate(LocalDateTime.now());
        return repository.getProjectionById(repository.saveAndFlush(client).getId()).get();
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public ClientProjection update(Long id, RecordClient model) {
        var personId = model.personId();

        var entity = repository.findById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Cliente"));
        personJpaRepository.findById(personId)
                .orElseThrow(CommonValidation.throwEntityNotfound("Pessoa"));

        if (entity.getId() != personId)
            CommonValidation.throwBusinessRuleViolation(
                    "A pessoa associada a esse cliente é diferente da que foi especificada");
        mapper.toEntity(entity, model);
        repository.saveAndFlush(entity);
        return repository.getProjectionById(id).get();
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        CommonValidation.throwExceptionIfInvalidId(id, "Cliente");
        var client = repository.findById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Cliente"));
        repository.deleteById(id);
        personJpaRepository.deleteById(client.getPerson().getId());
    }

}
