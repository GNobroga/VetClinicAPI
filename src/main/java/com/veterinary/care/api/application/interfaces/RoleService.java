package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordRole;
import com.veterinary.care.api.domain.entities.RoleEntity;
import com.veterinary.care.api.domain.projection.RoleProjection;

public interface RoleService extends GenericService<RoleEntity, RecordRole, RoleProjection> {

}
