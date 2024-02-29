package com.veterinary.care.api.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.application.interfaces.GenericService;
import com.veterinary.care.api.domain.entities.base.BaseEntity;

import jakarta.persistence.EntityManager;

public class GenericServiceImpl<TEntity extends BaseEntity, TModel, TProjection, TRepository extends JpaRepository<TEntity, Long>> implements GenericService<TEntity, TModel, TProjection> {

    @Autowired
    protected TRepository repository;

    @Autowired
    protected EntityManager entityManager;

    @Override
    public Page<TProjection> findAll(PageRequest pageRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public TProjection findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public TProjection create(TModel model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public TProjection update(Long id, TModel model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
