package com.veterinary.care.api.application.interfaces;

public interface CepService {

    void validateAndThrowIfInvalidCep(String cep);

    String normalizeCep(String cep);
}
