package com.veterinary.care.api.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.interfaces.AddressService;
import com.veterinary.care.api.application.interfaces.CepService;
import com.veterinary.care.api.application.mappers.AddressMapper;
import com.veterinary.care.api.application.models.RecordAddressWithPersonId;
import com.veterinary.care.api.domain.projection.AddressProjection;
import com.veterinary.care.api.insfrastructure.AddressJpaRepository;
import com.veterinary.care.api.insfrastructure.PersonJpaRepository;

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
        if (id == null)
            throw new NegociationException("O id não pode ser nulo");

        var address = repository.findById(id);

        if (!address.isPresent())
            throw new NegociationException("Não existe endereço com a identificação informada");

        return repository.getProjectionById(id);
    }

    @SuppressWarnings("null")
    @Override
    public AddressProjection create(RecordAddressWithPersonId model) {

        if (model.personId() == null)
            throw new NegociationException("A identificação da pessoa é necessária");

        if (!cepService.validate(cepService.normalizeCep(model.zipCode()))) {
            throw new NegociationException("O cep não foi encontrado");
        }

        var optionalPerson = personJpaRepository.findById(model.personId());

        if (!optionalPerson.isPresent()) {
            throw new NegociationException("Não há pessoa com a identificação informada");
        }

        var person = optionalPerson.get();
        var entity = mapper.toEntity(model);
        entity.setPerson(person);
        return repository.getProjectionById(repository.save(entity).getId());
    }

    @Override
    public AddressProjection update(Long id, RecordAddressWithPersonId model) {
        if (id == null)
            throw new NegociationException("O id não pode ser nulo");

        var entity = repository.findById(id).orElseThrow(() -> new NegociationException("O endereço não está cadastrado"));


        return null;

    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NegociationException("O id não pode ser nulo");
        repository.deleteById(id);
    }

}
