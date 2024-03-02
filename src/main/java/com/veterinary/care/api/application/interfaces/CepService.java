package com.veterinary.care.api.application.interfaces;

public interface CepService {

    boolean validate(String cep);

    String normalizeCep(String cep);
}
