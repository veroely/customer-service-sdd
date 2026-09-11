# Data Model

## Entity: Cliente

| Field | Type | Constraints | Description |
|---|---|---|---|
| idInterno | UUID | Read-only, generated | Identificador técnico inmutable del cliente. |
| identificacion | string | Required, unique | Número de identificación de negocio. |
| email | string | Required, unique, valid email format | Correo electrónico del cliente. |
| telefono | string | Required, unique | Número telefónico del cliente. |
| estado | enum | ACTIVO/DESACTIVADO | Estado de uso del cliente. |
| fechaCreacion | date-time | Required, immutable | Fecha de creación del registro. |
| fechaActualizacion | date-time | Required | Fecha del último cambio válido. |

## Validation Rules

- Los campos obligatorios: identificación, email, teléfono y estado inicial deben validarse antes de persistir.
- La identificación, el email y el teléfono deben ser únicos entre clientes.
- El identificador interno no puede modificarse tras la creación.
- La fecha de creación no puede cambiarse después del registro.
- La desactivación cambia el estado a `DESACTIVADO` y conserva el cliente persistido.

## State Transitions

- `ACTIVO -> DESACTIVADO` mediante operación de baja lógica.
- `DESACTIVADO -> ACTIVO` no se define en la especificación; el flujo por defecto solo soporta creación, consulta, listado, actualización y desactivación.

## Relationships

- Un cliente es una entidad independiente del servicio y no tiene relaciones de negocio adicionales en este alcance.
- La relación entre la ruta de consulta por identificador interno y la de identificación es de equivalencia documental entre dos localizadores del mismo registro.
