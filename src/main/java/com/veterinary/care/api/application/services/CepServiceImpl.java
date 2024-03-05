package com.veterinary.care.api.application.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.veterinary.care.api.application.interfaces.CepService;
import com.veterinary.care.api.application.utils.CommonValidation;

@Service
public class CepServiceImpl implements CepService {

    @Value("${viacep-api.baseUrl}")
    private String apiUrl;

    @SuppressWarnings("null")
    @Override
    public void validateAndThrowIfInvalidCep(String cep) {
        try {
            cep = normalizeCep(cep);
            RestTemplate restTemplate = new RestTemplate();
            var response = restTemplate.getForEntity(String.format("%s/%s/json", apiUrl, cep), JsonNode.class);
            var property = response.getBody().get("erro");
            if (property != null)
                throw new Exception();
        } catch (Exception ex) {
            CommonValidation.throwBusinessRuleViolation("CEP inválido");
        }
    }

    @Override
    public String normalizeCep(String cep) {
        return cep.replaceAll("[^\\d]", "");
    }

}
