package com.veterinary.care.api.domain.entities;


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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_vets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Veterinary extends BaseEntity {
    
    @Column(name = "registration_date", nullable = false)
    private String registrationDate;

    @Column(name = "specialty", nullable = false, length = 255)
    private String specialty;

    @Column(name = "crmv", length = 45, nullable = false)
    private String crmv;

    @Column(name = "crmv_uf", length = 2, nullable = false)
    private String crmvUf;

    @JoinColumn(name = "person_id", nullable = false)
    @ManyToOne
    private Person person;

    @OneToMany(mappedBy = "veterinary")
    private List<Attendance> attendances = new ArrayList<>();
}
