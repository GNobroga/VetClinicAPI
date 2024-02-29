package com.veterinary.care.api.domain.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.veterinary.care.api.domain.entities.base.BaseEntity;
import com.veterinary.care.api.application.enums.PersonType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tb_people")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonEntity extends BaseEntity {

    @Column(name = "name",  length = 255, nullable = false)
    private String name;

    @Column(name = "person_type",  length = 8, nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonType type;

    @Column(name = "cpf_cnpj", length = 14, nullable = false)
    private String document;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    
    @Column(name = "phone", length = 45, nullable = false)
    private String phone;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private ClientEntity client;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private VeterinaryEntity veterinary;

    @Builder.Default
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<AddressEntity> addresses = new ArrayList<>();
}
