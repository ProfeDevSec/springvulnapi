# Proyecto de Microservicio de Solicitudes

Este proyecto es un microservicio construido con **Spring Boot 2.7**, que simula un sistema de gestión de solicitudes bancarias. Incluye funcionalidades de consulta, registro y visualización de solicitudes de tipo crédito, hipoteca o tarjeta, junto con un mecanismo de persistencia en memoria utilizando **H2 Database**.

Este entorno ha sido desarrollado con fines **educativos y de entrenamiento en seguridad de aplicaciones Java**.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 2.7
- Spring Data JPA
- H2 Database (modo memoria, con consola web)
- Gradle
- Docker y Docker Compose

---

## 🛠️ Instrucciones de uso local

### 1. Clonar el repositorio

```bash
git clone https://github.com/tuusuario/solicitudes-seguras.git
cd solicitudes-seguras
```

### 2. Construcción y ejecución con Gradle

```bash
./gradlew buildJar
./gradlew runApp
```

### 3. Acceso a la aplicación

- API Base: `http://localhost:8080/solicitudes`
- Consola H2: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:solicitudesdb`
  - Usuario: `sa`
  - Contraseña: *(vacío)*

### 4. Datos precargados

Al iniciar el microservicio, se precargan automáticamente registros de ejemplo en la base de datos desde `data.sql`.

---

## 🧪 Uso con Docker

```bash
docker-compose build
docker-compose up
```

---

## ⚠️ Vulnerabilidades presentes (para análisis educativo)

Este proyecto contiene de forma **intencionada** algunas de las siguientes vulnerabilidades para que los estudiantes puedan:

- Identificarlas mediante análisis de código
- Reproducirlas manualmente
- Comprender su impacto y posibles mitigaciones

### Vulnerabilidades incluidas:

| Tipo de vulnerabilidad | Descripción general |
|------------------------|---------------------|
| **Inyección SQL (SQLi)** | Una funcionalidad permite ejecutar consultas SQL con entrada manipulable por el usuario. |
| **Manejo inseguro de errores** | El sistema puede revelar detalles de errores internos en formato sin sanitizar. |
| **Exposición de consola H2** | La consola de administración de la base de datos está expuesta públicamente. |
| **Endpoint sin validación** | Algunos endpoints aceptan parámetros sin validar su contenido. |
| **Dependencias desactualizadas** | El sistema puede incluir versiones de librerías con CVEs conocidos. |

Existen más vulnerabilidades que deberás encontrar e identificar...

---

## 🎓 Objetivo académico

El propósito de este microservicio es servir como base para prácticas de:

- Análisis estático de código
- Pentesting de aplicaciones Java
- Ejercicios de DevSecOps
- Evaluación de herramientas SAST/DAST

Los estudiantes deben investigar e identificar por su cuenta **dónde se encuentran estas vulnerabilidades** y proponer soluciones adecuadas.

---

## 📚 Licencia

Este proyecto es de uso académico y está licenciado bajo MIT.
