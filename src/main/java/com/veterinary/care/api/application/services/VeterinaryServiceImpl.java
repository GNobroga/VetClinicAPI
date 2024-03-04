package com.veterinary.care.api.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.enums.PersonType;
import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.interfaces.VeterinaryService;
import com.veterinary.care.api.application.mappers.VeterinaryMapper;
import com.veterinary.care.api.application.models.RecordVeterinary;
import com.veterinary.care.api.domain.projection.VeterinaryProjection;
import com.veterinary.care.api.insfrastructure.PersonJpaRepository;
import com.veterinary.care.api.insfrastructure.VeterinaryJpaRepository;

import jakarta.transaction.Transactional;

@Service
public class VeterinaryServiceImpl implements VeterinaryService {

    @Autowired
    private VeterinaryJpaRepository repository;

    @Autowired
    private PersonJpaRepository personJpaRepository;

    private VeterinaryMapper mapper = VeterinaryMapper.INSTANCE;

    @Override
    public List<VeterinaryProjection> findAll(PageRequest pageRequest) {
        return repository.getAllProjections(pageRequest).getContent();
    }

    @Override
    public VeterinaryProjection findById(Long id) {
        return repository.getProjectionById(id)
            .orElseThrow(() -> new NegociationException("Veterinário não encontrado"));
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public VeterinaryProjection create(RecordVeterinary model) {

        var personId = model.personId();

        var person = personJpaRepository
            .findById(personId).orElseThrow(() -> new NegociationException("Pessoa não encontrada"));

        var personType = person.getType();

        if (personType != null || (personType != null && personType.equals(PersonType.CLIENT)))
            throw new NegociationException("Essa pessoa já possui um tipo associado");

        repository.findByCrmv(model.crmv())
            .orElseThrow(() -> new NegociationException("Já existe um veterinário com o crmv informado"));

        person.setType(PersonType.VETERINARY);

        var entity = mapper.toEntity(model);

        person.setVeterinary(entity);
        entity.setPerson(person);

        entity = repository.saveAndFlush(entity);

        return repository.getProjectionById(entity.getId()).get();
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public VeterinaryProjection update(Long id, RecordVeterinary model) {
        if (id == null)
            throw new NegociationException("A identificação é obrigatória");

        var entity = repository.findById(id)
            .orElseThrow(() -> new NegociationException("Veterinário não encontrado"));

        if (entity.getCrmv() != model.crmv() && repository.findByCrmv(model.crmv()).isPresent())
            throw new NegociationException("Já existe um veterinário com o crmv informado");

        var personId = model.personId();

        var person = personJpaRepository
            .findById(personId).orElseThrow(() -> new NegociationException("Pessoa não encontrada"));

        var personType = person.getType();

        if (personType == null || !personType.equals(PersonType.VETERINARY))
            throw new NegociationException("A pessoa associada a essa identificação não é um veterinário");

        mapper.toEntity(entity, model);

        repository.saveAndFlush(entity);

        return repository.getProjectionById(id).get();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NegociationException("A identificação é obrigatória");
        repository.deleteById(id);
    }
}