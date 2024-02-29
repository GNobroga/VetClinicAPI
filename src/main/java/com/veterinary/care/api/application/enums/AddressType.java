package com.veterinary.care.api.application.enums;

import lombok.Getter;

@Getter
public enum AddressType {
    HOME("HOME"),
    WORK("WORK");

    private String value;

    private AddressType(String value) {
        this.value = value;
    }
}