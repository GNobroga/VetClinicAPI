package com.veterinary.care.api.domain.entities;

import java.time.LocalDate;
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
@Table(name = "tb_dogs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DogEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "breed", nullable = true, length = 255)
    private String breed;

    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;

    @Column(name = "registration_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime registrationDate;

    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne
    private ClientEntity client;

    @Builder.Default
    @OneToMany(mappedBy = "dog")
    private List<AttendanceEntity> attendances = new ArrayList<>();

}
