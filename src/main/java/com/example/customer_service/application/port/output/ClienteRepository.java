package com.example.customer_service.application.port.output;

import com.example.customer_service.model.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(UUID id);
    Optional<Cliente> findByNumeroIdentificacion(String numeroIdentificacion);
}
