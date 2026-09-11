# Research

## Decision: ruta de consulta dual
La especificación se resuelve con dos rutas de consulta explícitas para el cliente: una por identificador interno y otra por número de identificación. Esta decisión evita convertir el identificador interno en la única clave pública y respeta la intención del flujo de usuario.

**Rationale:** El escenario de usuario ya distingue consultas por identificador interno y por número de identificación. Apoyar ambos caminos reduce ambigüedad y simplifica la prueba de aceptación del consumidor.

**Alternatives considered:**
- Usar solo una clave de consulta.
- Pasar la identificación por query parameter en una única ruta.
- Usar una sola ruta con discriminación de tipo de clave interna.

## Decision: modelo de baja lógica
La desactivación se modelará como cambio de estado a `DESACTIVADO` y no como borrado físico.

**Rationale:** La especificación exige conservar la información del registro y evitar eliminación física mientras el cliente queda no activo.

**Alternatives considered:**
- Borrado físico del registro.
- Marcado visual en el cliente sin persistir el estado.

## Decision: contratos y datos de entrada
El contrato OpenAPI se considera origen del flujo de servicio. Los modelos de entrada y salida deben mantenerse sincronizados con el contrato del repositorio y la implementación generada.

**Rationale:** El proyecto ya usa OpenAPI y el plugin de generación de servidor en Gradle. Esto garantiza que la especificación pública se convierta en el punto de acuerdo entre el controlador, la capa de aplicación y el dominio.

**Alternatives considered:**
- Definir el contrato solo en la implementación.
- Hacer cambios en el controlador primero y regenerar el contrato después.
