package com.veterinary.care.api.domain.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_vets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VeterinaryEntity extends BaseEntity {

    @Column(name = "registration_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime registrationDate;

    @Column(name = "specialty", nullable = false, length = 255)
    private String specialty;

    @Column(name = "crmv", length = 45, nullable = false)
    private String crmv;

    @Column(name = "crmv_uf", length = 2, nullable = false)
    private String crmvUf;

    @JoinColumn(name = "person_id", nullable = false)
    @ManyToOne
    private PersonEntity person;

    @Builder.Default
    @OneToMany(mappedBy = "veterinary")
    private List<AttendanceEntity> attendances = new ArrayList<>();
}
