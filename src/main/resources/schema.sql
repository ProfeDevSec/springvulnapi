CREATE TABLE solicitud (
    id BIGINT PRIMARY KEY,
    rut_cliente VARCHAR(20),
    nombre_cliente VARCHAR(100),
    tipo_solicitud VARCHAR(50),
    estado VARCHAR(20),
    observaciones VARCHAR(500),
    fecha_creacion DATE
);