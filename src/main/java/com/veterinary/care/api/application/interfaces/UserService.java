package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordUser;
import com.veterinary.care.api.domain.entities.UserEntity;
import com.veterinary.care.api.domain.projection.UserProjection;

public interface UserService extends GenericService<UserEntity, RecordUser, UserProjection> {

}
