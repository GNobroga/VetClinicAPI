package com.veterinary.care.api.application.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.interfaces.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private JwtEncoder encoder;

    @Override
    public String generateToken(org.springframework.security.core.Authentication authentication) {
        var now = Instant.now();
        var expiry = 3600L;
        var issuer = "veterinary_api";

        String scopes = authentication.getAuthorities()
            .stream()
            .map(scope -> scope.getAuthority())
            .collect(Collectors.joining(","));

        var jwtClaims = JwtClaimsSet.builder()
            .issuedAt(now)
            .issuer(issuer)
            .expiresAt(now.plus(expiry, ChronoUnit.MINUTES))
            .subject(authentication.getName())
            .claims(addClaims -> addClaims.put("scope", scopes))
            .build();

            System.out.println(authentication.getName());

        return encoder.encode(JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), jwtClaims)).getTokenValue();
    }



}
