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
}
