package com.banco.solicitudes.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RestTemplate restTemplate = new RestTemplate();

    static class Usuario {
        public String username;
        public String password;
        public String rol;

        public Usuario(String username, String password, String rol) {
            this.username = username;
            this.password = password;
            this.rol = rol;
        }
    }

    private List<Usuario> usuarios = Arrays.asList(
        new Usuario("admin", "admin123", "ADMIN"),
        new Usuario("operador", "1234", "USER")
    );

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    @GetMapping("/endpointExterno")
     public ResponseEntity<String> endpointVulnerable(@RequestParam(defaultValue = "http://localhost:8080/solicitudes") String url) {
        try {
            String respuesta = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok("Respuesta desde: " + url + "\n\n" + respuesta);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al contactar el destino: " + ex.getMessage());
        }
    }
}