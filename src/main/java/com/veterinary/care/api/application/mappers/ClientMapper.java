package com.veterinary.care.api.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.veterinary.care.api.application.models.RecordClient;
import com.veterinary.care.api.domain.entities.ClientEntity;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    @Mappings({
        @Mapping(target = "dogs", ignore = true),
        @Mapping(target = "person", ignore = true),
        @Mapping(target = "registrationDate", ignore = true)
    })
    ClientEntity toEntity(RecordClient source);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "dogs", ignore = true),
        @Mapping(target = "person", ignore = true),
        @Mapping(target = "registrationDate", ignore = true)
    })
    void toEntity(@MappingTarget ClientEntity target, RecordClient source);

}
