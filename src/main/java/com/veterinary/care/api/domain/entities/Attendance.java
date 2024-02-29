package com.veterinary.care.api.domain.entities;

import java.time.LocalDateTime;
import java.util.Date;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_attendances")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attendance extends BaseEntity {

    @Column(name = "attendance_date", nullable = false)
    private LocalDateTime attendanceDate;
    
    @Column(name = "diagnosis", nullable = true)
    private String diagnosis;

    @Column(name = "comments", nullable = true)
    private String comments;

    @Column(name = "dog_weight", nullable = true)
    private Float dogWeight;

    @Column(name = "dog_height", nullable = true)
    private Float dogHeight;

    @Column(name = "dog_temperament", length = 255, nullable = true)
    private String dogTemperament;

    @JoinColumn(name = "dog_id", nullable = false)
    @ManyToOne
    private Dog dog;

    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne
    private Client client;

    @JoinColumn(name = "vet_id", nullable = false)
    @ManyToOne
    private Veterinary veterinary;
}
