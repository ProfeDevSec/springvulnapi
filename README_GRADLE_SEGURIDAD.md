# Seguridad aplicada en Gradle

Este proyecto implementa buenas prácticas de seguridad en la gestión de dependencias usando **Gradle**, 
con el objetivo de mejorar la **reproducibilidad del build**, la **protección frente a vulnerabilidades de la cadena de suministro**, y la **observabilidad de cambios** en librerías externas.

---

## Medidas de seguridad implementadas

### 1. Fijación de versiones (Version Pinning)
Todas las dependencias han sido definidas con **versiones exactas** para evitar la inclusión accidental de versiones nuevas con vulnerabilidades.


---

### 2. Bloqueo de dependencias (`dependency locking`)
Se ha activado `dependencyLocking` para fijar la resolución exacta de versiones a nivel de build.

Ejecuta este comando desde la raíz del proyecto:
```bash
   ./gradlew dependencies --write-locks
```
para:
- Resuelve todas las dependencias de tu proyecto
- Escribe el archivo gradle.lockfile en la raíz
- El archivo contendrá solo las coordenadas GAV (group:name:version) necesarias

- Archivo generado: `gradle.lockfile`
- Esto asegura que **todos los builds usen exactamente las mismas versiones**, evitando inconsistencias entre entornos.

```groovy
dependencyLocking {
    lockAllConfigurations()
}
```

---

### 3. Verificación de integridad (`verification-metadata.xml`)
Se activa la verificación de las dependencias descargadas usando **hashes SHA-256**, que validan que los artefactos no hayan sido alterados.

- Archivo generado: `gradle/verification-metadata.xml`
- Previene ataques de suplantación de artefactos (supply chain compromise)

Para generar hashes:
```bash
./gradlew --write-verification-metadata sha256 help
```

---

### 4. Compilación estricta
Se activó la validación de código fuente con flags estrictos para el compilador Java:

```groovy
tasks.withType(JavaCompile) {
    options.compilerArgs += ["-Xlint:all", "-Werror"]
}
```

- Esto convierte todas las advertencias en errores, elevando la calidad del código.

---

### 5. JUnit Platform activado
El sistema de testeo usa explícitamente `useJUnitPlatform()` para soportar JUnit 5 y detección de pruebas moderna.

```groovy
tasks.withType(Test) {
    useJUnitPlatform()
}
```

---

## Archivos relevantes generados

| Archivo                          | Propósito |
|----------------------------------|-----------|
| `gradle.lockfile`                | Resolución exacta de dependencias usadas |
| `gradle/verification-metadata.xml` | Hashes de seguridad de artefactos |
| `build.gradle`                   | Build configurado con seguridad y versionado |
