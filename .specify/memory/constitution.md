<!-- Sync Impact Report
Version change: 1.0.0 → 1.1.0
List of modified principles: I. Arquitectura Hexagonal, II. Lógica de negocio libre de infraestructura, III. Contrato OpenAPI primero, IV. Auditoría de controlador, V. Idioma y secreto libre
Added sections: none
Removed sections: none
Follow-up TODOs: TODO(RATIFICATION_DATE): Original ratification date is not recorded in repo context
-->

# Customer Service Constitution

## Core Principles

### I. Arquitectura Hexagonal
El proyecto DEBE implementar una arquitectura hexagonal con puertos y adaptadores. La lógica de negocio DEBE estar separada de los detalles de infraestructura, marcos, persistencia, comunicación externa y transporte. Cada flujo de entrada o salida DEBE cruzar una frontera explícita de puerto y adaptador antes de llegar a la capa de dominio. Rationale: La separación del dominio permite cambiar tecnología sin alterar la intención de negocio.

### II. Lógica de Negocio Independiente
La lógica de negocio DEBE permanecer independiente de frameworks, infraestructura, persistencia y comunicación externa. Las reglas y comportamientos de negocio DEBEN expresarse en entidades, casos de uso y puertos del dominio, no en clases acopladas al entorno de ejecución. Rationale: La capacidad de prueba, evolución y reutilización del dominio depende de su aislamiento declarativo.

### III. Contrato OpenAPI Primero
La creación o modificación de cualquier endpoint DEBE comenzar con la definición o actualización del contrato OpenAPI. No DEBE implementarse ningún endpoint que no esté definido en el contrato OpenAPI. Cualquier cambio en un endpoint existente DEBE reflejarse primero en el contrato OpenAPI antes de modificar su implementación. Rationale: El contrato es la fuente autorizada del servicio y protege a consumidores y adaptadores.

### IV. Auditoría de Entrada y Salida
El controlador DEBE registrar logs de auditoría para conocer la entrada recibida y la salida generada por cada operación. El contenido de dichos logs DEBE evitar secretos y DEBE conservar el contexto mínimo necesario para investigación y trazabilidad. Rationale: La observabilidad del flujo de solicitudes es un requisito de cumplimiento y soporte operativo.

### V. Lenguaje y Secretos
El idioma principal del proyecto DEBE ser español en artefactos de código, documentación y conversaciones de trabajo que definan el comportamiento del servicio. El código fuente NO DEBE contener claves, contraseñas, tokens, credenciales, certificados privados ni ningún otro secreto. Rationale: El idioma y la ausencia de secretos son condiciones de confianza, trazabilidad y seguridad para cualquier entrega.

## Additional Constraints

Este repositorio DEBE tratar la capa de dominio, la capa de aplicación y la capa de infraestructura como responsabilidades distintas. La interfaz pública y su contrato fiel DEBEN quedar en el archivo OpenAPI de origen y cualquier generación de código o modelo derivado DEBE considerarse artefacto derivado del contrato, nunca el origen del acuerdo. Los cambios de schema, rutas y respuestas DEBEN mantenerse sincronizados entre OpenAPI e implementación.

## Development Workflow

La construcción de nuevos endpoints o cambios de comportamiento DEBE seguir este flujo: definir o actualizar el contrato OpenAPI, documentar el cambio de forma verificable, crear o ajustar la prueba que compruebe el comportamiento, y luego implementar la respuesta mínima necesaria. La revisión de código DEBE comprobar que el endpoint exista en el contrato, que el flujo del controlador registre auditoría, y que el código fuente no contenga secretos ni dependencias de infraestructura en la capa de negocio.

## Governance

Esta Constitución prevalece sobre prácticas informales cuando exista conflicto. Las enmiendas DEBEN registrarse con documentación, revisión y un plan de migración cuando cambien comportamientos existentes. La revisión de cumplimiento DEBE verificar que cada principio sea medible, que los endpoints sigan el contrato OpenAPI, que la capa de negocio permanezca aislada de infraestructura y que la auditoría y la ausencia de secretos se preserven en la entrega.

**Version**: 1.1.0 | **Ratified**: TODO(RATIFICATION_DATE): Original ratification date is not recorded in repo context | **Last Amended**: 2026-09-11
