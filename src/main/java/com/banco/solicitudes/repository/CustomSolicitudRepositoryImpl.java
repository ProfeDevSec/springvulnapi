package com.banco.solicitudes.repository;

import com.banco.solicitudes.model.Solicitud;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomSolicitudRepositoryImpl implements CustomSolicitudRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Solicitud> buscarPorNombre(String nombre) {
        // TODO Aun vulnerable a desarrollar por el estudiante
        String sql = "SELECT * FROM solicitud WHERE nombre_cliente LIKE '%" + nombre + "%'";
        return entityManager.createNativeQuery(sql, Solicitud.class).getResultList();
    }
}
