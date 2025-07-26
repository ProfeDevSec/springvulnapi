package com.banco.solicitudes.repository;

import com.banco.solicitudes.model.Solicitud;
import java.util.List;

public interface CustomSolicitudRepository {
    List<Solicitud> buscarPorNombre(String nombre);
}
