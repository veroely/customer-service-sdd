package com.example.customer_service;

import com.example.customer_service.application.service.ClienteService;
import com.example.customer_service.infrastructure.adapter.output.InMemoryClienteRepository;
import com.example.customer_service.model.Cliente;
import com.example.customer_service.model.ClienteEntrada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    @Test
    void registrarClienteDebeCrearClienteConEstadoActivo() {
        ClienteService service = new ClienteService(new InMemoryClienteRepository());

        ClienteEntrada entrada = new ClienteEntrada();
        entrada.setTipoIdentificacion("CEDULA");
        entrada.setNumeroIdentificacion("1234567890");
        entrada.setNombres("Ana");
        entrada.setApellidos("García");
        entrada.setEmail("ana@example.com");
        entrada.setTelefono("0912345678");

        Cliente cliente = service.registrarCliente(entrada);

        assertNotNull(cliente);
        assertEquals(Cliente.EstadoEnum.ACTIVO, cliente.getEstado());
        assertEquals("1234567890", cliente.getNumeroIdentificacion());
        assertNotNull(cliente.getId());
    }

    @Test
    void registrarClienteDebeGuardarClienteEnRepositorioPorIdentificacion() {
        InMemoryClienteRepository repository = new InMemoryClienteRepository();
        ClienteService service = new ClienteService(repository);

        ClienteEntrada entrada = new ClienteEntrada();
        entrada.setTipoIdentificacion("CEDULA");
        entrada.setNumeroIdentificacion("0987654321");
        entrada.setNombres("Luis");
        entrada.setApellidos("Pérez");
        entrada.setEmail("luis@example.com");
        entrada.setTelefono("0999999999");

        Cliente cliente = service.registrarCliente(entrada);

        assertNotNull(cliente);
        assertEquals(Cliente.EstadoEnum.ACTIVO, cliente.getEstado());
        assertEquals("0987654321", cliente.getNumeroIdentificacion());
        assertNotNull(cliente.getId());
        assertNotNull(cliente.getFechaCreacion());
        assertNotNull(cliente.getFechaActualizacion());

        assertTrue(repository.findByNumeroIdentificacion("0987654321").isPresent());
        assertEquals(cliente.getId(), repository.findByNumeroIdentificacion("0987654321").get().getId());
    }
}
