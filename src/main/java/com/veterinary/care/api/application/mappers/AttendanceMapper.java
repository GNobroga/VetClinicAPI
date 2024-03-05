package com.veterinary.care.api.application.mappers;

import java.math.BigDecimal;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.veterinary.care.api.application.models.RecordAttendance;
import com.veterinary.care.api.domain.entities.AttendanceEntity;

@Mapper
public interface AttendanceMapper {

    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    @Mappings({
        @Mapping(target = "client", ignore = true),
        @Mapping(target = "dog", ignore = true),
        @Mapping(target = "veterinary", ignore = true)
    })
    AttendanceEntity toEntity(RecordAttendance source);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "client", ignore = true),
        @Mapping(target = "dog", ignore = true),
        @Mapping(target = "veterinary", ignore = true)
    })
    void toEntity(@MappingTarget AttendanceEntity target, RecordAttendance source);

    @AfterMapping
    default void afterMapping(@MappingTarget AttendanceEntity target, RecordAttendance source) {
        target.setPrice(source.price() == null ? new BigDecimal(0) : source.price());
    }
}
