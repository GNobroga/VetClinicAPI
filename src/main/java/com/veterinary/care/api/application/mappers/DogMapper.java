package com.veterinary.care.api.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.veterinary.care.api.application.models.RecordDog;
import com.veterinary.care.api.domain.entities.DogEntity;

@Mapper
public interface DogMapper {

    DogMapper INSTANCE = Mappers.getMapper(DogMapper.class);

    @Mappings({
        @Mapping(target = "attendances", ignore = true),
        @Mapping(target = "client", ignore = true)
    })
    DogEntity toEntity(RecordDog source);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "attendances", ignore = true),
        @Mapping(target = "client", ignore = true)
    })
    void toEntity(@MappingTarget DogEntity target, RecordDog source);
}
