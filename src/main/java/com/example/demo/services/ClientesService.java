package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Clientes;

public interface ClientesService {

    List<Clientes> getAllClientes();

    Optional<Clientes> getClienteById(Long id);

    void saveCliente(Clientes cliente);

    void deleteCliente(Long id);
}
