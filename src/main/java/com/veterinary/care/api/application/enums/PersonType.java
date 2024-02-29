package com.veterinary.care.api.application.enums;

import lombok.Getter;

@Getter
public enum PersonType {
    CLIENT("CLIENT"),
    VETERINARY("VETERINARY");

    private String value;

    private PersonType(String value) {
        this.value = value;
    }
}
