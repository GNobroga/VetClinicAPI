package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordAddressWithPersonId;
import com.veterinary.care.api.domain.entities.AddressEntity;
import com.veterinary.care.api.domain.projection.AddressProjection;

public interface AddressService extends GenericService<AddressEntity, RecordAddressWithPersonId, AddressProjection> {

}
