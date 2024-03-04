package com.veterinary.care.api.application.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.interfaces.DogService;
import com.veterinary.care.api.application.mappers.DogMapper;
import com.veterinary.care.api.application.models.RecordDog;
import com.veterinary.care.api.domain.projection.DogProjection;
import com.veterinary.care.api.insfrastructure.ClientJpaRepository;
import com.veterinary.care.api.insfrastructure.DogJpaRepository;

import jakarta.transaction.Transactional;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogJpaRepository repository;

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    private DogMapper mapper = DogMapper.INSTANCE;

    @Override
    public List<DogProjection> findAll(PageRequest pageRequest) {
       return repository.getAllProjections(pageRequest).getContent();
    }

    @Override
    public DogProjection findById(Long id) {
        return repository.getProjectionById(id)
            .orElseThrow(() -> new NegociationException("Cachorro não encontrado"));
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public DogProjection create(RecordDog model) {

        var client = clientJpaRepository
            .findById(model.clientId())
            .orElseThrow(() -> new NegociationException("Cliente não encontrado"));

        var entity = mapper.toEntity(model);

        client.getDogs().add(entity);
        entity.setClient(client);
        entity.setRegistrationDate(LocalDate.now());

        entity = repository.saveAndFlush(entity);

        return repository.getProjectionById(entity.getId()).get();
    }

    @SuppressWarnings("null")
    @Override
    public DogProjection update(Long id, RecordDog model) {

        var entity = repository.findById(id)
            .orElseThrow(() -> new NegociationException("Cachorro não encontrado"));

        var client = clientJpaRepository
            .findById(model.clientId())
            .orElseThrow(() -> new NegociationException("Cliente não encontrado"));

        if (model.clientId() != client.getId())
            throw new NegociationException("Cliente não corresponde ao que foi enviado");

        mapper.toEntity(entity, model);

        repository.saveAndFlush(entity);

        return repository.getProjectionById(id).get();
    }

    @Override
    public void delete(Long id) {
       if (id == null)
            throw new NegociationException("A identificação é obrigatória");
        repository.deleteById(id);
    }
}