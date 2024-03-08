package com.veterinary.care.api.application.configuration;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private String secretKey;

    @PostConstruct
    void generateSecretKey() {
        byte[] secretKeyBytes = new byte[32]; // 32 bytes = 256 bits (para HMAC SHA-256)
        new SecureRandom().nextBytes(secretKeyBytes);
        secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizeRequests -> {
                authorizeRequests.requestMatchers("/swagger-ui/**", "/api-docs/**", "/api/v1/auth/**").permitAll();
                authorizeRequests.anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer(config -> {
                config.jwt(Customizer.withDefaults());
            })
            .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKey originalKey = new SecretKeySpec(secretKey.getBytes(), "RSA");
        return NimbusJwtDecoder.withSecretKey(originalKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "RSA");
        var immutableSecret = new ImmutableSecret<>(key);
        return new NimbusJwtEncoder(immutableSecret);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
