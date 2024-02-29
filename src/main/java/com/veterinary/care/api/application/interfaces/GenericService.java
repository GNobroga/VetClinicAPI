package com.veterinary.care.api.application.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

public interface GenericService<TEntity extends BaseEntity, TModel> {
    
    Page<TModel> findAll(PageRequest pageRequest);

    TModel findById(Long id);

    TModel create(TModel model);

    TModel update(Long id, TModel model);

    boolean delete(Long id);
}
