package com.veterinary.care.api.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.interfaces.AddressService;
import com.veterinary.care.api.application.interfaces.CepService;
import com.veterinary.care.api.application.mappers.AddressMapper;
import com.veterinary.care.api.application.models.RecordAddressWithPerson;
import com.veterinary.care.api.application.utils.CommonValidation;
import com.veterinary.care.api.domain.projection.AddressProjection;
import com.veterinary.care.api.insfrastructure.AddressJpaRepository;
import com.veterinary.care.api.insfrastructure.PersonJpaRepository;

import jakarta.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressJpaRepository repository;

    @Autowired
    private PersonJpaRepository personJpaRepository;

    @Autowired
    private CepService cepService;

    private AddressMapper mapper = AddressMapper.INSTANCE;

    @Override
    public List<AddressProjection> findAll(PageRequest pageRequest) {
        return repository.findAllWithProjection(pageRequest).getContent();
    }

    @Override
    public AddressProjection findById(Long id) {
        CommonValidation.throwExceptionIfInvalidId(id, "Endereço");
        return repository.getProjectionById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Endereço"));
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public AddressProjection create(RecordAddressWithPerson model) {

        cepService.validateAndThrowIfInvalidCep(cepService.normalizeCep(model.zipCode()));

        var person = personJpaRepository.findById(model.personId())
                .orElseThrow(CommonValidation.throwEntityNotfound("Pessoa"));
        var entity = mapper.toEntity(model);
        entity.setPerson(person);

        var id = repository.saveAndFlush(entity).getId();
        return repository.getProjectionById(id).orElseThrow();
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public AddressProjection update(Long id, RecordAddressWithPerson model) {
        CommonValidation.throwExceptionIfInvalidId(id, "Endereço");
        var entity = repository.findById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Endereço"));

        if (entity.getId() != model.personId())
            CommonValidation.throwBusinessRuleViolation("Esse endereço não pertence a pessoa especificada");

        cepService.validateAndThrowIfInvalidCep(cepService.normalizeCep(model.zipCode()));

        if (entity.getPerson().getId() != model.personId())
            CommonValidation.throwBusinessRuleViolation("Identificação da pessoa diferente da prevista");

        mapper.toEntity(entity, model);

        repository.saveAndFlush(entity);

        return repository.getProjectionById(id).orElseThrow();

    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        CommonValidation.throwExceptionIfInvalidId(id, "Address");
        repository.findById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Endereço"));
        repository.deleteById(id);
    }

}
