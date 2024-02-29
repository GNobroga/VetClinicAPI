package com.veterinary.care.api.domain.entities;

import com.veterinary.care.api.domain.entities.base.BaseEntity;
import com.veterinary.care.api.application.enums.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address extends BaseEntity {

    @Column(name = "public_place", length = 255, nullable = false)
    private String place; 

    @Column(name = "number", length = 15, nullable = false)
    private String number;

    @Column(name = "complement", nullable = true)
    private String complement;

    @Column(name = "zip_code", length =  255, nullable = false)
    private String zipCode;

    @Column(name = "addresss_type", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType type;

    @JoinColumn(name = "person_id", nullable = false)
    @ManyToOne
    private Person person;
   
}
