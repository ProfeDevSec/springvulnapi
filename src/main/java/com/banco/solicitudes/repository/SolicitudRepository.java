package com.banco.solicitudes.repository;

import com.banco.solicitudes.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long>, CustomSolicitudRepository {
    // Aquí puedes seguir usando métodos seguros también
}