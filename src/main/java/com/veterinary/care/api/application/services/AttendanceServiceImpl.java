package com.veterinary.care.api.application.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.interfaces.AttendanceService;
import com.veterinary.care.api.application.mappers.AttendanceMapper;
import com.veterinary.care.api.application.models.RecordAttendance;
import com.veterinary.care.api.application.utils.CommonValidation;
import com.veterinary.care.api.domain.projection.AttendanceProjection;
import com.veterinary.care.api.insfrastructure.AttendanceJpaRepository;
import com.veterinary.care.api.insfrastructure.ClientJpaRepository;
import com.veterinary.care.api.insfrastructure.DogJpaRepository;
import com.veterinary.care.api.insfrastructure.VeterinaryJpaRepository;

import jakarta.transaction.Transactional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceJpaRepository repository;

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    @Autowired
    private VeterinaryJpaRepository veterinaryJpaRepository;

    @Autowired
    private DogJpaRepository dogJpaRepository;

    private AttendanceMapper mapper = AttendanceMapper.INSTANCE;

    @Override
    public List<AttendanceProjection> findAll(PageRequest pageRequest) {
        return repository.getAllByProjection(pageRequest).getContent();
    }

    @Override
    public AttendanceProjection findById(Long id) {
        CommonValidation.throwExceptionIfInvalidId(id, "Atendimento");
        return repository.getByProjectionId(id)
                .orElseThrow(CommonValidation.throwEntityNotfound("Atendimento"));
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public AttendanceProjection create(RecordAttendance model) {
        var dog = dogJpaRepository.findById(model.dogId())
                .orElseThrow(CommonValidation.throwEntityNotfound("Cachorro"));
        var client = clientJpaRepository.findById(model.clientId())
                .orElseThrow(CommonValidation.throwEntityNotfound("Cliente"));
        var veterinary = veterinaryJpaRepository.findById(model.veterinaryId())
                .orElseThrow(CommonValidation.throwEntityNotfound("Veterinário"));
        var entity = mapper.toEntity(model);
        entity.setAttendanceDate(LocalDateTime.now());


        dog.getAttendances().add(entity);
        entity.setDog(dog);
        entity.setClient(client);
        entity.setVeterinary(veterinary);
        var id = repository.saveAndFlush(entity).getId();
        return repository.getByProjectionId(id).orElseThrow();
    }

    @Transactional
    @SuppressWarnings("null")
    @Override
    public AttendanceProjection update(Long id, RecordAttendance model) {
        CommonValidation.throwExceptionIfInvalidId(id, "Atendimento");
        var entity = repository.findById(id).orElseThrow(CommonValidation.throwEntityNotfound("Atendimento"));

        if (entity.getDog().getId() != model.dogId() || entity.getClient().getId() != model.clientId() || entity.getVeterinary().getId() != model.veterinaryId())
            CommonValidation.throwBusinessRuleViolation("Inconsistência nos dados");

        mapper.toEntity(entity, model);
        repository.saveAndFlush(entity);
        return repository.getByProjectionId(id).orElseThrow();
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public void delete(Long id) {
        CommonValidation.throwExceptionIfInvalidId(id, "Atendimento");
        repository.findById(id).orElseThrow(CommonValidation.throwEntityNotfound("Atendimento"));
        repository.deleteById(id);
    }

}