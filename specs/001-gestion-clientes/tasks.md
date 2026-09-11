# Tasks: Gestión de Clientes

**Input**: Design documents from `/specs/001-gestion-clientes/`

**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: The examples below include test tasks. Tests are OPTIONAL - only include them if explicitly requested in the feature specification.

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and basic structure

- [ ] T001 Create feature contract and model documentation in `specs/001-gestion-clientes/contracts/clientes-api.yaml`
- [ ] T002 Ensure OpenAPI generation in Gradle remains aligned with the repository source contract in `src/main/resources/openapi/customer-spec.yaml`
- [ ] T003 [P] Define the customer domain model and validation vocabulary in `src/main/java/com/example/customer_service/domain/model/`

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure that MUST be complete before any user story can be implemented

- [x] T004 Define customer entity, request, and response record shape for the repository adapter model in `src/main/java/com/example/customer_service/model/`
- [x] T005 Implement the inbound customer HTTP adapter interface and controller integration points in `src/main/java/com/example/customer_service/infrastructure/adapter/input/`
- [x] T006 Implement the domain application service port definitions in `src/main/java/com/example/customer_service/application/port/`
- [x] T007 Create the repository adapter fulfilment boundary in `src/main/java/com/example/customer_service/infrastructure/adapter/output/`
- [ ] T008 Configure audit logging around controller entry and exit for request and response traceability

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Registrar cliente (Priority: P1) 🎯 MVP

**Goal**: Provide the business flow that allows a client to be created and validated before persistence.

**Independent Test**: A valid create request should return `201` and persist a customer with `ACTIVO` state plus creation and update audit timestamps.

### Tests for User Story 1 (OPTIONAL - only if tests requested) ⚠️

- [ ] T009 [P] [US1] Contract test for `POST /api/clientes` in `src/test/java/com/example/customer_service/`
- [ ] T010 [P] [US1] Integration test for customer registration in `src/test/java/com/example/customer_service/`

### Implementation for User Story 1

- [x] T011 [P] [US1] Add the create customer command and validation use case in `src/main/java/com/example/customer_service/application/service/`
- [x] T012 [US1] Implement create endpoint in the generated controller interface or controller adapter in `src/main/java/com/example/customer_service/infrastructure/adapter/input/`
- [ ] T013 [US1] Bind the client creation validation and uniqueness rules in the domain/application layer
- [ ] T014 [US1] Register audit logging for request and response in the controller path

**Checkpoint**: At this point, User Story 1 should be fully functional and testable independently

---

## Phase 4: User Story 2 - Consultar cliente por identificador e identificación (Priority: P1)

**Goal**: Support public customer lookup by internal identifier and by business identification.

**Independent Test**: A client created by US1 can be fetched through both lookup routes and returns the same resource representation.

### Tests for User Story 2 (OPTIONAL - only if tests requested) ⚠️

- [ ] T015 [P] [US2] Contract test for the explicit dual lookup routes `GET /api/clientes/{id}` and `GET /api/clientes/identificacion/{identificacion}` based on the contract in `specs/001-gestion-clientes/contracts/clientes-api.yaml`.
- [ ] T016 [P] [US2] Integration test for fetch by internal ID and fetch by business identification in `src/test/java/com/example/customer_service/`

### Implementation for User Story 2

- [ ] T017 [P] [US2] Implement query use cases in `src/main/java/com/example/customer_service/application/service/`
- [ ] T018 [US2] Implement lookup adapters in `src/main/java/com/example/customer_service/infrastructure/adapter/output/`
- [ ] T019 [US2] Add controller retrieval endpoints to the service adapter layer in `src/main/java/com/example/customer_service/infrastructure/adapter/input/`
- [ ] T020 [US2] Register audit logging for the lookup request and response in the controller path

**Checkpoint**: At this point, User Stories 1 AND 2 should both work independently

---

## Phase 5: User Story 3 - Listar clientes con paginación (Priority: P2)

**Goal**: Provide a paginated listing endpoint for customers while preserving the active and disabled record distinction.

**Independent Test**: A request to `GET /api/clientes` with page and size parameters returns the page structure containing only the requested customer slice.

### Tests for User Story 3 (OPTIONAL - only if tests requested) ⚠️

- [ ] T021 [P] [US3] Contract test for list pagination in `src/test/java/com/example/customer_service/`
- [ ] T022 [P] [US3] Integration test for listing a paginated response in `src/test/java/com/example/customer_service/`

### Implementation for User Story 3

- [ ] T023 [P] [US3] Implement list query use case and page normalization in `src/main/java/com/example/customer_service/application/service/`
- [ ] T024 [US3] Implement repository adapter pagination query in `src/main/java/com/example/customer_service/infrastructure/adapter/output/`
- [ ] T025 [US3] Add controller GET list endpoint and page response mapping in `src/main/java/com/example/customer_service/infrastructure/adapter/input/`
- [ ] T026 [US3] Add audit input and output logging for list operations

**Checkpoint**: All user stories should now be independently functional

---

## Phase 6: User Story 4 - Actualizar y desactivar cliente (Priority: P1)

**Goal**: Enforce update validation and carry out logical deactivation without deleting stored information.

**Independent Test**: Updating metadata preserves creation date, updates the modification timestamp, and deactivation changes state to `DESACTIVADO` without physical deletion.

### Tests for User Story 4 (OPTIONAL - only if tests requested) ⚠️

- [ ] T027 [P] [US4] Contract test for update and deactivate flows in `src/test/java/com/example/customer_service/`
- [ ] T028 [P] [US4] Integration test for update and logical disable behavior in `src/test/java/com/example/customer_service/`

### Implementation for User Story 4

- [ ] T029 [P] [US4] Implement update and deactivate use cases in `src/main/java/com/example/customer_service/application/service/`
- [ ] T030 [US4] Add update and delete-state boundary logic in the repository adapter in `src/main/java/com/example/customer_service/infrastructure/adapter/output/`
- [ ] T031 [US4] Implement `PUT /api/clientes/{id}` and logical deactivation flow in the controller adapter in `src/main/java/com/example/customer_service/infrastructure/adapter/input/`
- [ ] T032 [US4] Ensure audit logging captures request and response payloads for update and disable actions and masks any secrets

**Checkpoint**: At this point, User Story 4 should be fully functional and testable independently

---

## Phase 7: Polish & Cross-Cutting Concerns

**Purpose**: Cross-cutting validation and consistency across all user stories

- [ ] T033 Add consistent error mapping and validation response messages in the infrastructure controller and domain layer.
- [ ] T034 Add a repository-level uniqueness and conflict validation enforcement in the application and adapter layers.
- [ ] T035 Verify OpenAPI contract and generated server interfaces remain synchronized with controller and model changes.
- [ ] T036 Confirm no source file contains keys, passwords, tokens, credentials, certificates, or other secrets.
- [ ] T037 Confirm Spanish source and documentation naming remains consistent across the implementation artifacts.
