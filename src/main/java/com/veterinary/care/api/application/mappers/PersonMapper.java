package com.veterinary.care.api.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.veterinary.care.api.application.models.RecordPerson;
import com.veterinary.care.api.domain.entities.PersonEntity;
import com.veterinary.care.api.domain.entities.UserEntity;

@Mapper(uses = { AddressMapper.class } )
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "veterinary", ignore = true)
    @Mapping(target = "user", expression = "java(mapUsernameAndPasswordToUser(source.username(), source.password()))")
    @Mapping(target = "email", expression = "java(source.email().toLowerCase())")
    PersonEntity toEntity(RecordPerson source);

    default UserEntity mapUsernameAndPasswordToUser(String username, String password) {
        return UserEntity
            .builder()
            .username(username)
            .password(password)
            .build();
    }

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "veterinary", ignore = true)
    @Mapping(target = "user", ignore = true)
    void toEntity(@MappingTarget PersonEntity target,  RecordPerson source);
}
