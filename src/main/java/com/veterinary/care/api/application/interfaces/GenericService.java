package com.veterinary.care.api.application.interfaces;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

public interface GenericService<E extends BaseEntity, M, P> {

    List<P> findAll(PageRequest pageRequest);

    P findById(Long id);

    P create(M model);

    P update(Long id, M model);

    void delete(Long id);
}
