package com.veterinary.care.api.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PersonType {
    CLIENT("client", "pessoa que solicita um atendimento"),
    VETERINARY("veterinary", "pessoa que realiza um atendimento");

    private final String type;
    private final String description;
}
