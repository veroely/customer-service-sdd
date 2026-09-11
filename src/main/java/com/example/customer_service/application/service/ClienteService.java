package com.example.customer_service.application.service;

import com.example.customer_service.application.port.output.ClienteRepository;
import com.example.customer_service.model.Cliente;
import com.example.customer_service.model.ClienteEntrada;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente registrarCliente(ClienteEntrada entrada) {
        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(entrada.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(entrada.getNumeroIdentificacion());
        cliente.setNombres(entrada.getNombres());
        cliente.setApellidos(entrada.getApellidos());
        cliente.setEmail(entrada.getEmail());
        cliente.setTelefono(entrada.getTelefono());
        cliente.setId(UUID.randomUUID());
        cliente.setEstado(Cliente.EstadoEnum.ACTIVO);
        cliente.setFechaCreacion(OffsetDateTime.now());
        cliente.setFechaActualizacion(OffsetDateTime.now());

        return clienteRepository.save(cliente);
    }
}
