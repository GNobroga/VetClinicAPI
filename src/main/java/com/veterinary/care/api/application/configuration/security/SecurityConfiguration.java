// package com.veterinary.care.api.application.configuration.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// import com.veterinary.care.api.application.filters.LoggerFilter;

// // @Configuration
// // @EnableWebSecurity
// // public class SecurityConfiguration {

// //     @Bean
// //     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// //         return http
// //             .csrf(csrf -> csrf.disable())
// //             .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// //             .authorizeHttpRequests(authorizeRequests -> {
// //                 authorizeRequests.anyRequest().permitAll();
// //             })
// //             .addFilter(new LoggerFilter())
// //             .build();
// //     }

// //     // @Bean
// //     // AuthenticationManager authenticationManager() {

// //     // }

// //     @Bean
// //     PasswordEncoder passwordEncoder() {
// //         return new BCryptPasswordEncoder();
// //     }
// // }
