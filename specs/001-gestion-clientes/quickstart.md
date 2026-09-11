# Quickstart

## Prerrequisitos

- Java 21
- Gradle wrapper del proyecto
- Base de datos o almacenamiento del servicio configurado en el entorno
- Registro de auditoría en el controlador habilitado para la entrada y salida

## Validación inicial

1. Ejecutar la compilación del proyecto con Gradle.
2. Verificar que el contrato OpenAPI y la implementación generada comparten el mismo conjunto de rutas.
3. Ejecutar las pruebas de integración de gestión de clientes.

## Escenarios de validación

1. Crear un cliente con datos válidos.
2. Consultar cliente por identificador interno.
3. Consultar cliente por identificación.
4. Listar clientes con paginación.
5. Actualizar cliente y verificar mantenimiento de la fecha de creación.
6. Desactivar cliente y verificar cambio a estado `DESACTIVADO` sin borrado físico.
7. Intentar crear o actualizar un cliente con identificacion, email o teléfono duplicado y verificar rechazo.
