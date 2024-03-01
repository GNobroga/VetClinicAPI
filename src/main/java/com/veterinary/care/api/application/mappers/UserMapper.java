package com.veterinary.care.api.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.veterinary.care.api.application.models.RecordUser;
import com.veterinary.care.api.domain.entities.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE  = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "person", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserEntity toEntity(RecordUser source);
}
