package com.veterinary.care.api.application.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Clinica Veterinária API")
                .description("Uma API que provê CRUD para algumas entidades relacionadas a uma clínica veterinária.")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Gabriel Cardoso Girarde")
                    .email("gabrielcardoso_stelo@hotmail")
                )
            );
    }
}
