package com.veterinary.care.api.domain.converters;

import jakarta.persistence.AttributeConverter;

public class CepConvert implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
      return attribute.replaceAll("[^\\d]", "");
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }

}
