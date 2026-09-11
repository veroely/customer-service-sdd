package com.example.customer_service.infrastructure.adapter.output;

import com.example.customer_service.application.port.output.ClienteRepository;
import com.example.customer_service.model.Cliente;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryClienteRepository implements ClienteRepository {

    private final Map<UUID, Cliente> clientesPorId = new ConcurrentHashMap<>();
    private final Map<String, Cliente> clientesPorIdentificacion = new ConcurrentHashMap<>();

    @Override
    public Cliente save(Cliente cliente) {
        clientesPorId.put(cliente.getId(), cliente);
        clientesPorIdentificacion.put(cliente.getNumeroIdentificacion(), cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> findById(UUID id) {
        return Optional.ofNullable(clientesPorId.get(id));
    }

    @Override
    public Optional<Cliente> findByNumeroIdentificacion(String numeroIdentificacion) {
        return Optional.ofNullable(clientesPorIdentificacion.get(numeroIdentificacion));
    }
}
