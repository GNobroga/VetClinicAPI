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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client extends BaseEntity {
    
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate = LocalDate.now();

    @JoinColumn(name = "person_id", nullable = false)
    @OneToOne
    private Person person;

    @OneToMany(mappedBy = "client")
    private List<Dog> dogs = new ArrayList<>();
}
