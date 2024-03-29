package com.veterinary.care.api.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_attendances")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceEntity extends BaseEntity {

    @Column(name = "attendance_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime attendanceDate;

    @Column(name = "diagnosis", nullable = true)
    private String diagnosis;

    @Column(name = "comments", nullable = true)
    private String comments;

    @Column(name = "dog_weight", nullable = true, columnDefinition = "NUMERIC(6, 2)")
    private Double dogWeight;

    @Column(name = "dog_height", nullable = true, columnDefinition = "NUMERIC(6, 2)")
    private Double dogHeight;

    @Column(name = "dog_temperament", length = 255, nullable = true)
    private String dogTemperament;

    @Column(name = "price", columnDefinition = "NUMERIC(10, 2)")
    private BigDecimal price;

    @JoinColumn(name = "dog_id", nullable = false)
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private DogEntity dog;

    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private ClientEntity client;

    @JoinColumn(name = "vet_id", nullable = false)
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private VeterinaryEntity veterinary;
}
