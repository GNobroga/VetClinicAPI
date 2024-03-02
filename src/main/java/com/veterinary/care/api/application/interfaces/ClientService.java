package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordClient;
import com.veterinary.care.api.domain.entities.ClientEntity;
import com.veterinary.care.api.domain.projection.ClientProjection;

public interface ClientService extends GenericService<ClientEntity, RecordClient, ClientProjection> {

}
