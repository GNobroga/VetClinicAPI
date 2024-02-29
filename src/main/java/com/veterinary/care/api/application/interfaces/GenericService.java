package com.veterinary.care.api.application.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

public interface GenericService<TEntity extends BaseEntity, TRecordModel, TProjectionModel> {
    
    Page<TProjectionModel> findAll(PageRequest pageRequest);

    TProjectionModel findById(Long id);

    TProjectionModel create(TRecordModel model);

    TProjectionModel update(Long id, TRecordModel model);

    boolean delete(Long id);
}
