package com.example.customer_service.infrastructure.adapter.input;

import com.example.customer_service.DefaultApi;
import com.example.customer_service.application.service.ClienteService;
import com.example.customer_service.model.Cliente;
import com.example.customer_service.model.ClienteEntrada;
import com.example.customer_service.model.PaginaClientes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClienteController implements DefaultApi {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public ResponseEntity<Cliente> registrarCliente(ClienteEntrada clienteEntrada) {
        Cliente cliente = clienteService.registrarCliente(clienteEntrada);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cliente> consultarClientePorId(UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<PaginaClientes> consultarClientes(Integer pagina, Integer tamano) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Cliente> actualizarCliente(UUID id, String ifMatch, ClienteEntrada clienteEntrada) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> desactivarCliente(UUID id, String ifMatch) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Cliente> consultarClientePorIdentificacion(String numeroIdentificacion) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
