package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.application.projection.PersonProjection;
import com.veterinary.care.api.domain.entities.PersonEntity;

public interface PersonService extends GenericService<PersonEntity, RecordPerson, PersonProjection> {
    
    
}
