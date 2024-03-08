package com.veterinary.care.api.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.interfaces.PersonService;
import com.veterinary.care.api.application.mappers.PersonMapper;
import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.application.utils.CommonValidation;
import com.veterinary.care.api.domain.entities.AddressEntity;
import com.veterinary.care.api.domain.projection.PersonProjection;
import com.veterinary.care.api.insfrastructure.PersonJpaRepository;
import com.veterinary.care.api.insfrastructure.RoleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PersonJpaRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    private PersonMapper mapper = PersonMapper.INSTANCE;

    @Override
    public List<PersonProjection> findAll(PageRequest pageRequest) {
        return repository.findAllWithProjection(pageRequest).getContent();
    }

    @Override
    public PersonProjection findById(Long id) {
        CommonValidation.throwExceptionIfInvalidId(id, "Pessoa");
        return repository.getProjectionById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Pessoa"));
    }

    @Override
    @Transactional
    public PersonProjection create(RecordPerson model) {
        if (model.addresses().isEmpty())
            CommonValidation.throwBusinessRuleViolation("Cadastre pelo menos 1 endereço");

        if (!repository.findByEmailOrUsername(model.email(), model.username()).isEmpty())
            CommonValidation.throwBusinessRuleViolation("Email ou username não estão disponíveis para uso");

        if (repository.findByDocument(normalizeDocument(model.document())).isPresent())
            CommonValidation.throwBusinessRuleViolation("Documento não está disponível para uso");

        var entity = mapper.toEntity(model);
        var passwordCrypted = passwordEncoder.encode(entity.getUser().getPassword());
        entity.getUser().setPassword(passwordCrypted);
        var optionalRole = roleJpaRepository.findByName("USER");

        if (optionalRole.isPresent()) {
            var role = optionalRole.get();
            entity.getUser().getRoles().add(role);
        }

        entity.getAddresses().forEach(x -> x.setPerson(entity));
        return repository.getProjectionById(repository.save(entity).getId()).get();
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public PersonProjection update(Long id, RecordPerson model) {
        CommonValidation.throwExceptionIfInvalidId(id, "Pessoa");
        var user = repository.findById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Pessoa"));

        if (!normalizeDocument(user.getDocument()).equals(normalizeDocument(model.document()))
                && repository.findByDocument(model.document()).isPresent())
            CommonValidation.throwBusinessRuleViolation("Documento não está disponível para uso");

        if ((!user.getUser().getUsername().equalsIgnoreCase(model.username())
                && repository.findByUsername(model.username()).isPresent()) ||
                (!user.getEmail().equalsIgnoreCase(model.email()) && repository.findByEmail(model.email()).isPresent()))
            CommonValidation.throwBusinessRuleViolation("Email ou username não estão disponíveis para uso");

        // Se o usuário mandar um endereço eu apago todos os existentes.
        if (!model.addresses().isEmpty()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<AddressEntity> delete = cb.createCriteriaDelete(AddressEntity.class);
            Root<AddressEntity> root = delete.from(AddressEntity.class);
            delete.where(cb.equal(root.get("person").get("id"), id));
            entityManager.createQuery(delete).executeUpdate();
        }

        // Se passou nas validações acima eu transfiro as informações do model para
        // entity
        mapper.toEntity(user, model);
        var passwordCrypted = passwordEncoder.encode(user.getUser().getPassword());
        user.getUser().setPassword(passwordCrypted);
        user.getAddresses().forEach(x -> x.setPerson(user));
        repository.saveAndFlush(user);

        return repository.getProjectionById(id).get();
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        CommonValidation.throwExceptionIfInvalidId(id, "Pessoa");
        repository.findById(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Pessoa"));
        repository.deleteById(id);
    }

    private static String normalizeDocument(String document) {
        return document.replaceAll("[^\\d]", "");
    }

}
