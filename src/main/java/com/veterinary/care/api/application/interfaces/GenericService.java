package com.veterinary.care.api.application.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

public interface GenericService<E extends BaseEntity, M, P> {
    
    Page<P> findAll(PageRequest pageRequest);

    P findById(Long id);

    P create(M model);

    P update(Long id, M model);

    boolean delete(Long id);
}
