package com.veterinary.care.api.application.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("VETCLINIC API")
                .description("Uma API que provê um CRUD para pessoas, veterinários, etc.")
                .version("1.0.0"));
    } 
}
