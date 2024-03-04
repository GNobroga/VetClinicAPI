package com.veterinary.care.api.application.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.enums.PersonType;
import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.interfaces.ClientService;
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

        // Verificando se a pessoa já é um cliente
        if (repository.findByPersonId(model.personId()).isPresent())
            throw new NegociationException("O cliente já está associado a esse papel");

        // Se a pessoa for veterinário ela não pode ser um cliente, porque se não alteraria o seu papel
        if (person.getType() != null && person.getType().equals(PersonType.VETERINARY))
            throw new NegociationException("Um veterinário não pode ser cliente, pois já tem um papel.");

        if (person.getType() != null && person.getType().equals(PersonType.CLIENT))
            throw new NegociationException("Essa pessoa já é um cliente");

        // Adicionar o type CLIENT na pessoa
        person.setType(PersonType.CLIENT);

        ClientEntity client = ClientEntity.builder()
            .person(person)
            .registrationDate(LocalDate.now())
            .build();

       return repository.getProjectionById(repository.saveAndFlush(client).getId()).get();
    }

    /**
     * @param id Não será usado aqui, só está de enfeite
     */
    @Transactional
    @SuppressWarnings("null")
    @Override
    public ClientProjection update(Long id, RecordClient model) {
         // Vai apenas remover o papel de cliente do usuário

         var personId = model.personId();

         if (personId == null)
         throw new NegociationException("Identificação inválida");

        var person = personJpaRepository.findById(personId)
            .orElseThrow(() -> new NegociationException("Pessoa não encontrada"));

        var client = person.getClient();

        if (client == null)
            throw new NegociationException("Essa pessoa não é do tipo CLIENT");

        person.setType(null);
        person.setClient(null);
        repository.deleteById(client.getId());
        personJpaRepository.saveAndFlush(person);
        return null;
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NegociationException("Identificação inválida");

        var person = personJpaRepository.findById(id)
            .orElseThrow(() -> new NegociationException("Pessoa não encontrada"));

        var client = person.getClient();

        if (client == null)
            throw new NegociationException("Essa pessoa não é um cliente");

        repository.deleteById(client.getId());
        personJpaRepository.delete(person);
    }


}
