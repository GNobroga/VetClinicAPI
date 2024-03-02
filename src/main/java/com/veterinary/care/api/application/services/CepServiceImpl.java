package com.veterinary.care.api.application.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.veterinary.care.api.application.interfaces.CepService;

@Service
public class CepServiceImpl implements CepService {

    @Value("${viacep-api}")
    private String apiUrl;

    @SuppressWarnings("null")
    @Override
    public boolean validate(String cep) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            var response = restTemplate.getForEntity(String.format("%s/%s/json", apiUrl, cep), Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (RestClientException ex) {
            return false;
        }
    }

    @Override
    public String normalizeCep(String cep) {
        return cep.replaceAll("[^\\d]", "");
    }


}
