package com.veterinary.care.api.insfrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.care.api.domain.entities.AttendanceEntity;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

}
