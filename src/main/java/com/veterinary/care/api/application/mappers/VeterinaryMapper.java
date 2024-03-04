package com.veterinary.care.api.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.veterinary.care.api.application.models.RecordVeterinary;
import com.veterinary.care.api.domain.entities.VeterinaryEntity;

@Mapper
public interface VeterinaryMapper {

    VeterinaryMapper INSTANCE = Mappers.getMapper(VeterinaryMapper.class);

    @Mapping(target = "person", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    VeterinaryEntity toEntity(RecordVeterinary source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    void toEntity(@MappingTarget VeterinaryEntity target, RecordVeterinary source);
}
