package com.banco.solicitudes.controller;

import com.banco.solicitudes.model.Solicitud;
import com.banco.solicitudes.repository.SolicitudRepository;
import com.banco.solicitudes.util.InputSanitizer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudRepository repository;
    private static final Logger log = LoggerFactory.getLogger(SolicitudController.class);

    public SolicitudController(SolicitudRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Solicitud> getAll() {
        return repository.findAll(); // CWE-200: Exposición de datos
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Solicitud>> buscar(@RequestParam String nombre) {
        // CWE-89: Inyección SQL
        if (nombre == null || nombre.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        log.info("Buscando solicitudes para: " + nombre);
        List<Solicitud> resultado = repository.buscarPorNombre(nombre.trim());
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Solicitud> crear(@Valid @RequestBody Solicitud solicitud) {
        // XSS
        solicitud.setObservaciones(InputSanitizer.sanitizeHtml(solicitud.getObservaciones()));
        Solicitud creada = repository.save(solicitud);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> actualizar(@PathVariable Long id, @Valid @RequestBody Solicitud nueva) {
        return repository.findById(id).map(s -> {
            s.setEstado(nueva.getEstado());
            s.setObservaciones(InputSanitizer.sanitizeHtml(nueva.getObservaciones()));
            return ResponseEntity.ok(repository.save(s));
        }).orElse(ResponseEntity.notFound().build());
    }
}