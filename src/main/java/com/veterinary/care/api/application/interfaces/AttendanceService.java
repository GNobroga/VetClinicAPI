package com.veterinary.care.api.application.interfaces;

import com.veterinary.care.api.application.models.RecordAttendance;
import com.veterinary.care.api.domain.entities.AttendanceEntity;
import com.veterinary.care.api.domain.projection.AttendanceProjection;

public interface AttendanceService extends GenericService<AttendanceEntity, RecordAttendance, AttendanceProjection> {

}
