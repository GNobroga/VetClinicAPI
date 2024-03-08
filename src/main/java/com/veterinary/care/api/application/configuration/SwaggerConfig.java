package com.veterinary.care.api.application.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server-url}")
    private String serverUrl;

    private static final String JWT_AUTHENTICATION = "Bearer";

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clinica Veterinária API")
                        .description(
                                "Uma API que provê CRUD para algumas entidades relacionadas a uma clínica veterinária.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Gabriel Cardoso Girarde")
                                .email("gabrielcardoso_stelo@hotmail")
                                .url("https://github.com/GNobroga/VetClinicAPI")))
                .servers(Collections
                        .singletonList(new Server()
                                .url(serverUrl)
                                .description("Servidor de desenvolvimento")))
                .addSecurityItem(new SecurityRequirement().addList(JWT_AUTHENTICATION))
                .components(new Components()
                        .addSecuritySchemes(JWT_AUTHENTICATION, new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .scheme(JWT_AUTHENTICATION)
                                .name("Authorization")
                                .in(SecurityScheme.In.HEADER)
                                .description("Bearer token_value"))
                );
    }
}
