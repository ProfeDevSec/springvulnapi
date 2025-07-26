package com.banco.solicitudes.controller;

import com.banco.solicitudes.model.Solicitud;
import com.banco.solicitudes.repository.SolicitudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudRepository repository;
    private static final Logger log = LoggerFactory.getLogger(SolicitudController.class);

    public SolicitudController(SolicitudRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Solicitud> getAll() {
        return repository.findAll();
    }

    @GetMapping("/buscar")
    public List<Solicitud> buscar(@RequestParam String nombre) {
        log.info("Buscando solicitudes para: " + nombre);
        return repository.buscarPorNombre(nombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Solicitud crear(@RequestBody Solicitud solicitud) {
        return repository.save(solicitud);
    }

    @PutMapping("/{id}")
    public Solicitud actualizar(@PathVariable Long id, @RequestBody Solicitud nueva) {
        return repository.findById(id).map(s -> {
            s.setEstado(nueva.getEstado());
            s.setObservaciones(nueva.getObservaciones());
            return repository.save(s);
        }).orElse(null);
    }
}