package com.veterinary.care.api.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressType {
    HOME("home", "casa"),
    WORK("work", "trabalho");

    private final String type;
    private final String description;
}