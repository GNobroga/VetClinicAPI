package com.veterinary.care.api.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.veterinary.care.api.application.models.RecordAddress;
import com.veterinary.care.api.application.models.RecordAddressWithPersonId;
import com.veterinary.care.api.domain.entities.AddressEntity;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    @Mapping(target = "person", ignore = true)
    AddressEntity toEntity(RecordAddress source);

    @Mapping(target = "person", ignore = true)
    AddressEntity toEntity(RecordAddressWithPersonId source);

    void toEntity(AddressEntity target, @MappingTarget RecordAddressWithPersonId source);
}
