package com.veterinary.care.api.domain.converters;

import jakarta.persistence.AttributeConverter;

public class DocumentConvert implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String document) {
        return document.replaceAll("[^\\d]", "");
    }

    @Override
    public String convertToEntityAttribute(String document) {
        if (document.length() == 11) {
            return document.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return document.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$2");
    }
    
}
