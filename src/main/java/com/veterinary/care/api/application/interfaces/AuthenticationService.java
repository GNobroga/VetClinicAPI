package com.veterinary.care.api.application.interfaces;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    String authenticate(Authentication authentication);
}
