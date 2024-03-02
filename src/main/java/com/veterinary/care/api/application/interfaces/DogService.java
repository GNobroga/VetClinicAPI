package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordDog;
import com.veterinary.care.api.domain.entities.DogEntity;
import com.veterinary.care.api.domain.projection.DogProjection;

public interface DogService extends GenericService<DogEntity, RecordDog, DogProjection> {

}
