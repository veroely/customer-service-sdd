# Feature Specification: Gestión de Clientes

**Feature Branch**: `001-gestion-clientes`

**Created**: 2026-09-11

**Status**: Draft

**Input**: User description: "Objetivo y Contexto de negocio
El componente de Gestión de Clientes tiene como objetivo administrar la información básica de los clientes de la organización.
El componente debe permitir registrar, consultar, listar, actualizar y desactivar clientes, garantizando la integridad y unicidad de la información registrada.

Usuarios
Los principales usuarios del componente son los consumidores de la API de Gestión de Clientes.

Escenarios de usuario
US-01: Crear cliente
Como consumidor de la API
Quiero registrar un nuevo cliente
Para disponer de su información en el sistema.

US-02: Consultar cliente por identificador
Como consumidor de la API
Quiero consultar un cliente por su identificador interno
Para obtener su información.

US-03: Consultar cliente por identificación
Como consumidor de la API
Quiero consultar un cliente por su número de identificación
Para obtener su información sin conocer su identificador interno.

US-04: Listar clientes
Como consumidor de la API
Quiero consultar una lista de clientes
Para obtener información de los clientes registrados.

US-05: Actualizar cliente
Como consumidor de la API
Quiero actualizar la información de un cliente
Para mantener sus datos actualizados.

US-06: Desactivar cliente
Como consumidor de la API
Quiero desactivar un cliente
Para evitar su utilización como cliente activo sin eliminar físicamente su información.

Requisitos funcionales
RF-01 — El sistema DEBE permitir registrar un nuevo cliente.

RF-02 — El sistema DEBE permitir consultar un cliente mediante su identificador interno.

RF-03 — El sistema DEBE permitir buscar un cliente mediante su número de identificación.

RF-04 — El sistema DEBE permitir consultar una lista de clientes.El listado DEBE soportar paginación.

RF-05 — El sistema DEBE permitir actualizar la información de un cliente existente.

RF-06 — El sistema DEBE permitir desactivar un cliente mediante eliminación lógica.La operación NO DEBE eliminar físicamente la información almacenada.

RF-07 — El sistema DEBE validar los datos de entrada antes de procesar las operaciones. Cuando los datos no cumplan las validaciones establecidas, el sistema DEBE rechazar la operación e informar el motivo.

RF-08 — El sistema DEBE registrar:
Fecha de creación al registrar el cliente.
Fecha de actualización al modificar o desactivar el cliente.
La fecha de creación NO DEBE modificarse durante una actualización.

Reglas de negocio
RN-01 — La identificación de un cliente DEBE ser única. No pueden existir dos clientes con la misma identificación.

RN-02 — El email de un cliente DEBE ser único.No pueden existir dos clientes con el mismo email.

RN-03 — El teléfono de un cliente DEBE ser único.No pueden existir dos clientes con el mismo teléfono.

RN-04 — Todo cliente creado debe tener inicialmente el estado: ACTIVO

RN-05 — La eliminación de un cliente DEBE realizarse mediante un cambio de estado a: DESACTIVADO Los datos del cliente NO deben eliminarse físicamente.

RN-06 — El identificador interno del cliente NO puede modificarse.

RN-07 — La fecha de creación NO puede modificarse después de registrar el cliente.

RN-08 — Cuando se actualice la identificación, email o teléfono, el sistema DEBE validar que el nuevo valor no pertenezca a otro cliente. El cliente que está siendo actualizado puede conservar su propio valor actual.

Criterios de aceptación
CA-01 — Crear cliente correctamente
Dado que no existe un cliente con la identificación, email o teléfono proporcionados
Cuando se registra un cliente con información válida
Entonces el sistema debe crear el cliente con estado ACTIVO.

CA-02 — Rechazar identificación duplicada
Dado que existe un cliente con una determinada identificación
Cuando se intenta registrar otro cliente con la misma identificación
Entonces el sistema debe rechazar la operación.

CA-03 — Rechazar email duplicado
Dado que existe un cliente con un determinado email
Cuando se intenta registrar otro cliente con el mismo email
Entonces el sistema debe rechazar la operación.

CA-04 — Rechazar teléfono duplicado
Dado que existe un cliente con un determinado teléfono
Cuando se intenta registrar otro cliente con el mismo teléfono
Entonces el sistema debe rechazar la operación.

CA-05 — Consultar cliente existente
Dado que existe un cliente
Cuando se consulta utilizando su identificador
Entonces el sistema debe retornar la información del cliente.

CA-06 — Consultar por identificación
Dado que existe un cliente con una determinada identificación
Cuando se consulta utilizando dicha identificación
Entonces el sistema debe retornar la información del cliente.

CA-07 — Cliente inexistente
Dado que no existe un cliente con el identificador solicitado
Cuando se realiza una consulta
Entonces el sistema debe informar que el cliente no existe.

CA-08 — Actualizar cliente
Dado que existe un cliente
Cuando se actualiza su información con datos válidos
Entonces el sistema debe actualizar la información y modificar la fecha de actualización.

CA-09 — Mantener fecha de creación
Dado que existe un cliente
Cuando se actualiza su información
Entonces la fecha de creación debe permanecer sin cambios.

CA-10 — Desactivar cliente
Dado que existe un cliente activo
Cuando se solicita su eliminación
Entonces el sistema debe cambiar su estado a DESACTIVADO.

CA-11 — Mantener información después de desactivar
Dado que existe un cliente
Cuando el cliente es desactivado
Entonces su información debe permanecer almacenada.

CA-12 — Validar datos obligatorios
Dado que faltan uno o más datos obligatorios
Cuando se intenta crear o actualizar un cliente
Entonces el sistema debe rechazar la operación e informar los campos inválidos.

CA-13 — Validar formato de email
Dado que el email proporcionado no tiene un formato válido
Cuando se intenta crear o actualizar un cliente
Entonces el sistema debe rechazar la operación.

CA-14 — Unicidad durante actualización
Dado que existe un cliente A y un cliente B
Cuando se intenta actualizar el email, teléfono o identificación del cliente A utilizando el valor del cliente B
Entonces el sistema debe rechazar la operación."

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Registrar cliente (Priority: P1)

Como consumidor de la API, quiero registrar un nuevo cliente para disponer de su información en el sistema.

**Why this priority**: La creación de clientes constituye el punto de entrada al servicio y habilita el resto de operaciones de consulta, listado, actualización y baja lógica.

**Independent Test**: Puede probarse con una operación de creación válida y comprobar que el cliente queda registrado en estado ACTIVO con datos de auditoría.

**Acceptance Scenarios**:

1. **Given** no existe un cliente con la identificación, email o teléfono proporcionados, **When** el consumidor registra un cliente con información válida, **Then** el sistema crea el cliente en estado ACTIVO.
2. **Given** existe un cliente con la misma identificación, **When** se intenta registrar otro cliente con la misma identificación, **Then** el sistema rechaza la operación.

---

### User Story 2 - Consultar cliente por identificador e identificación (Priority: P1)

Como consumidor de la API, quiero consultar un cliente de dos formas: por identificador interno y por número de identificación para obtener su información sin depender de un dato técnico interno.

**Why this priority**: La recuperación del cliente es el flujo base de valor del servicio y exige consultas deterministas por claves de negocio.

**Independent Test**: Puede probarse creando un cliente y realizando una consulta por identificador interno y otra por identificación, verificando que ambas devoluciones corresponden al mismo cliente.

**Acceptance Scenarios**:

1. **Given** existe un cliente registrado, **When** el consumidor consulta por su identificador interno, **Then** el sistema devuelve la información del cliente.
2. **Given** existe un cliente registrado con una identificación determinada, **When** el consumidor consulta por su identificación, **Then** el sistema devuelve la información del cliente.
3. **Given** no existe un cliente con el identificador solicitado, **When** se realiza la consulta, **Then** el sistema informa que el cliente no existe.

---

### User Story 3 - Listar clientes con paginación (Priority: P2)

Como consumidor de la API, quiero consultar una lista de clientes ordenada y paginada para revisar el conjunto de clientes registrados.

**Why this priority**: El listado de clientes es una necesidad de consulta operativa y mejora la capacidad de navegación del consumidor del servicio.

**Independent Test**: Puede probarse insertando varios clientes y solicitando una página específica para verificar la respuesta lista y paginada.

**Acceptance Scenarios**:

1. **Given** existen clientes registrados, **When** el consumidor ejecuta una consulta de listado con paginación, **Then** el sistema devuelve el subconjunto solicitado y el contrato de paginación correspondiente.

---

### User Story 4 - Actualizar y desactivar cliente (Priority: P1)

Como consumidor de la API, quiero actualizar la información de un cliente y desactivarlo mediante eliminación lógica para mantener datos correctos sin perder la historia del cliente.

**Why this priority**: Estos flujos protegen la integridad del dato y permiten conservar la información del cliente aunque ya no esté activo.

**Independent Test**: Puede probarse creando un cliente, actualizando cualquiera de sus campos no modificables y luego desactivándolo, verificando que la fecha de actualización cambie y el estado se vuelva DESACTIVADO.

**Acceptance Scenarios**:

1. **Given** existe un cliente, **When** se actualiza su información con datos válidos, **Then** el sistema actualiza el cliente y registra la fecha de actualización sin alterar la fecha de creación.
2. **Given** existe un cliente activo, **When** se solicita la desactivación, **Then** el sistema cambia su estado a DESACTIVADO sin eliminar físicamente el registro.

---

### User Story 5 - Validación y unicidad de datos (Priority: P1)

Como consumidor de la API, quiero que el sistema valide los datos de entrada y rechace operaciones con inconsistencias para proteger la integridad y unicidad de la información registrada.

**Why this priority**: La integridad y unicidad son condiciones de negocio esenciales del componente y deben garantizarse antes de persistir o modificar datos.

**Independent Test**: Puede probarse con casos de identificación duplicada, email duplicado, teléfono duplicado, email inválido y campos obligatorios faltantes.

**Acceptance Scenarios**:

1. **Given** faltan datos obligatorios o el formato de email es inválido, **When** se intenta crear o actualizar un cliente, **Then** el sistema rechaza la operación e informa los campos inválidos.
2. **Given** existe un cliente A y un cliente B, **When** se intenta actualizar el email, teléfono o identificación del cliente A con un valor ya perteneciente al cliente B, **Then** el sistema rechaza la operación.

---

## Functional Requirements

1. El sistema DEBE permitir registrar un nuevo cliente con datos básicos válidos.
2. El sistema DEBE permitir consultar un cliente mediante su identificador interno.
3. El sistema DEBE permitir buscar un cliente mediante su número de identificación.
4. El sistema DEBE permitir consultar una lista de clientes sustentada en paginación.
5. El sistema DEBE permitir actualizar la información de un cliente existente sin alterar la fecha de creación ni el identificador interno.
6. El sistema DEBE permitir desactivar un cliente por eliminación lógica, manteniendo el registro almacenado y cambiando su estado a DESACTIVADO.
7. El sistema DEBE validar de forma previa la presencia y el formato de los datos necesarios para crear o actualizar clientes.
8. El sistema DEBE registrar la fecha de creación al crear el cliente y la fecha de actualización cuando el cliente sea modificado o desactivado.
9. El sistema DEBE asegurar la unicidad de la identificación, email y teléfono entre clientes activos o registrados.
10. El sistema DEBE impedir la actualización de la fecha de creación o del identificador interno del cliente.
11. El sistema DEBE informar al consumidor el motivo de rechazo cuando una operación no cumple las reglas de validación o de negocio.

## Success Criteria

1. El componente permite completar el flujo de registro, consulta por identificador, consulta por identificación, listado paginado, actualización y desactivación sin pérdida de datos ni alteración de la fecha de creación.
2. Al menos el 95% de las operaciones de creación y actualización se ejecutan con validación completa y sin duplicidad de identificación, email o teléfono.
3. La operación de desactivación cambia el estado del cliente a DESACTIVADO y conserva el registro físico para auditoría y trazabilidad.
4. Los consumidores reciben mensajes claros del motivo de cualquier rechazo por campos obligatorios, formato inválido o violación de unicidad.
5. Los clientes registrados pueden ser consultados por identificador interno o por identificación sin depender del número de identificación como clave técnica de almacenamiento.

## Key Entities

- Cliente: entidad principal del servicio que almacena la identificación, email, teléfono, estado, fecha de creación y fecha de actualización.
- Identificador interno: clave técnica inmutable del cliente utilizada para referencias internas.
- Identificación: número identificador único de negocio del cliente.
- Estado del cliente: valor ACTIVO o DESACTIVADO que determina si el cliente puede usarse de manera activa.

### Edge Cases

- Qué ocurre si el cliente se consulta con un identificador interno inexistente?
- Qué ocurre si el valor de identificación, email o teléfono que se pretende actualizar ya pertenece a otro cliente?
- Qué ocurre si el cliente ya fue desactivado y se solicita una nueva actualización o consulta de negocio?
- Qué ocurre si un cliente nuevo llega sin información obligatoria o con un email no válido?
- Qué ocurre si una desactivación se solicita sobre un cliente que ya está DESACTIVADO?
