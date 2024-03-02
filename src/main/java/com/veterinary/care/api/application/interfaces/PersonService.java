package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.domain.entities.PersonEntity;
import com.veterinary.care.api.domain.projection.PersonProjection;

public interface PersonService extends GenericService<PersonEntity, RecordPerson, PersonProjection> {

}
