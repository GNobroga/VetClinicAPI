package com.veterinary.care.api.application.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.enums.PersonType;
import com.veterinary.care.api.application.interfaces.VeterinaryService;
import com.veterinary.care.api.application.mappers.VeterinaryMapper;
import com.veterinary.care.api.application.models.RecordVeterinary;
import com.veterinary.care.api.application.utils.CommonValidation;
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
        CommonValidation.throwExceptionIfInvalidId(id, "Veterinário");
        return repository.getProjectionById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Veterinário"));
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public VeterinaryProjection create(RecordVeterinary model) {

        var personId = model.personId();

        var person = personJpaRepository
                .findById(personId).orElseThrow(CommonValidation.throwEntityNotfound("Pessoa"));

        var personType = person.getType();

        if (personType != null)
            CommonValidation.throwBusinessRuleViolation("Essa pessoa já possui um tipo associado");

        if (repository.findByCrmv(model.crmv()).isPresent())
            CommonValidation.throwBusinessRuleViolation("Já existe um veterinário com o crmv informado");

        person.setType(PersonType.VETERINARY);

        var entity = mapper.toEntity(model);

        person.setVeterinary(entity);
        entity.setPerson(person);
        entity.setRegistrationDate(LocalDateTime.now());

        entity = repository.saveAndFlush(entity);

        return repository.getProjectionById(entity.getId()).get();
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public VeterinaryProjection update(Long id, RecordVeterinary model) {
        CommonValidation.throwExceptionIfInvalidId(id, "Veterinário");

        var entity = repository.findById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Veterinário"));

        var person = entity.getPerson();

        if (person.getId() != model.personId())
            CommonValidation.throwBusinessRuleViolation("Inconsistência nos dados para atualização");

        if (!entity.getCrmv().equalsIgnoreCase(model.crmv()) && repository.findByCrmv(model.crmv()).isPresent())
            CommonValidation.throwBusinessRuleViolation("Já existe um veterinário com o crmv informado");

        var personType = person.getType();

        if (personType == null || !personType.equals(PersonType.VETERINARY))
            CommonValidation.throwBusinessRuleViolation("A pessoa associada a essa identificação não é um veterinário");

        mapper.toEntity(entity, model);
        repository.saveAndFlush(entity);
        return repository.getProjectionById(id).get();
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public void delete(Long id) {
        CommonValidation.throwExceptionIfInvalidId(id, "Veterinário");
        var veterinary = repository.findById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Veterinário"));
        repository.deleteById(id);
        personJpaRepository.deleteById(veterinary.getPerson().getId());
    }
}