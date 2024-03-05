package com.veterinary.care.api.domain.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.veterinary.care.api.domain.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientEntity extends BaseEntity {

    @Column(name = "registration_date", nullable = true, columnDefinition = "DATE")
    private LocalDate registrationDate;

    @Column(name = "communication_preferences", length = 100, nullable = true)
    private String communicationPreferences;

    @Column(name = "alternate_phone", length = 45, nullable = true)
    private String alternatePhone;

    @JoinColumn(name = "person_id", nullable = false)
    @OneToOne
    private PersonEntity person;

    @Builder.Default
    @OneToMany(mappedBy = "client")
    private List<DogEntity> dogs = new ArrayList<>();
}
