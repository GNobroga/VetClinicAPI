package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordVeterinary;
import com.veterinary.care.api.domain.entities.VeterinaryEntity;
import com.veterinary.care.api.domain.projection.VeterinaryProjection;

public interface VeterinaryService extends GenericService<VeterinaryEntity, RecordVeterinary, VeterinaryProjection> {

}
