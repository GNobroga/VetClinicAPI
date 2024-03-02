package com.veterinary.care.api.application.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.exceptions.NegociationException;
import com.veterinary.care.api.application.interfaces.PersonService;
import com.veterinary.care.api.application.mappers.PersonMapper;
import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.domain.entities.AddressEntity;
import com.veterinary.care.api.domain.projection.PersonProjection;
import com.veterinary.care.api.insfrastructure.PersonJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

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
        if (id == null)
            throw new NegociationException("É necessário informar uma identificação válida");
        return repository.getProjectionById(id);
    }

    @Override
    @Transactional
    public PersonProjection create(RecordPerson model) {
        if (model.addresses().isEmpty())
            throw new NegociationException("Cadastre pelo menos 1 endereço");

        if (!repository.findByEmailOrUsername(model.email(), model.username()).isEmpty())
            throw new NegociationException("Email ou username não estão disponíveis para uso");

        if (repository.findByDocument(normalizeDocument(model.document())).isPresent()) {
            throw new NegociationException("Documento não está disponível para uso");
        }

        var entity = mapper.toEntity(model);
        entity.getAddresses().forEach(x -> x.setPerson(entity));
        return repository.getProjectionById(repository.save(entity).getId());
    }

    @Override
    @Transactional
    public PersonProjection update(Long id, RecordPerson model) {
        if (id == null)
            throw new NegociationException("É necessário informar um Id válido");

        var user = repository.findById(id)
            .orElseThrow(() -> new NegociationException("Pessoa não encontrada"));

        if (!normalizeDocument(user.getDocument()).equals(normalizeDocument(model.document())) &&  repository.findByDocument(model.document()).isPresent())
            throw new NegociationException("Documento não está disponível para uso");


        if (
            (!user.getUser().getUsername().equalsIgnoreCase(model.username()) && repository.findByUsername(model.username()).isPresent()) ||
            (!user.getEmail().equalsIgnoreCase(model.email()) && repository.findByEmail(model.email()).isPresent())
        )
            throw new NegociationException("Email ou username não estão disponíveis para uso");


        // Se o usuário mandar um endereço eu apago todos os existentes.
        if (!model.addresses().isEmpty()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<AddressEntity> delete = cb.createCriteriaDelete(AddressEntity.class);
            Root<AddressEntity> root = delete.from(AddressEntity.class);
            delete.where(cb.equal(root.get("person").get("id"), id));
            entityManager.createQuery(delete).executeUpdate();

        }

        // Se passou nas validações acima eu transfiro as informações do model para entity
        mapper.toEntity(user,  model);

        user.getAddresses().forEach(x -> x.setPerson(user));
        repository.saveAndFlush(user);

        return repository.getProjectionById(id);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NegociationException("É preciso informar um Id válido");
        repository.deleteById(id);
    }

    private static String normalizeDocument(String document) {
        return document.replaceAll("[^\\d]", "");
    }



}
