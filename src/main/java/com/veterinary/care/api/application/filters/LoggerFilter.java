package com.veterinary.care.api.application.filters;

import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LoggerFilter extends OncePerRequestFilter {

    private Logger logger = Logger.getLogger(LoggerFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var path = request.getPathInfo();

        if (path.contains("/api")) {
            logger.info("entrando no doFilterInternal");
        }

        filterChain.doFilter(request, response);
    }
    
}
