package com.banco.solicitudes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Solo APIs REST, no web forms
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/solicitudes/**").permitAll()   // <--- RUTAS PÚBLICAS
                        .anyRequest().authenticated()                   // <--- RUTAS PRIVADAS
                )
                .httpBasic(); // Uso de HTTP Basic para pruebas

        return http.build();
    }

    // SOLO PARA EFECTOS DE PRUEBA
    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("adminpass")
                        .roles("ADMIN")
                        .build()
        );
    }
}
