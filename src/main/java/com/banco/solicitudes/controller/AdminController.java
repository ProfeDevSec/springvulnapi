package com.banco.solicitudes.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // Lista blanca explícita de hosts permitidos
    private static final List<String> DOMINIOS_PERMITIDOS = Arrays.asList(
            "api.ejemplo.com",
            "servicios.seguro.cl"
    );

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

    String defadminpass = BCrypt.hashpw("admin123", BCrypt.gensalt());
    String defpass = BCrypt.hashpw("1234", BCrypt.gensalt());

    private List<Usuario> usuarios = Arrays.asList(
        new Usuario("admin", defadminpass, "ADMIN"),
        new Usuario("operador", defpass, "USER")
    );

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        // CWE-200: fuga de información, credenciales en texto plano
        // TODO mejorar esta solución, tarea para el estudiante
        return usuarios;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/endpointExterno")
     public ResponseEntity<String> endpointVulnerable(@RequestParam(defaultValue = "http://localhost:8080/solicitudes") String url) {
        // CWE-918: Server-Side Request Forgery (SSRF)
        if (!esUrlValida(url)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL no permitida");
        }
        try {
            String respuesta = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok("Respuesta desde: " + url + "\n\n" + respuesta);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al contactar el destino: " + ex.getMessage());
        }
    }

    private boolean esUrlValida(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            return host != null && DOMINIOS_PERMITIDOS.contains(host);
        } catch (URISyntaxException e) {
            return false;
        }
    }
}