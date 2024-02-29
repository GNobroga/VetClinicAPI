package com.veterinary.care.api.application.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

public interface GenericService<TEntity extends BaseEntity, TModel, TProjection> {
    
    Page<TProjection> findAll(PageRequest pageRequest);

    TProjection findById(Long id);

    TProjection create(TModel model);

    TProjection update(Long id, TModel model);

    boolean delete(Long id);
}
