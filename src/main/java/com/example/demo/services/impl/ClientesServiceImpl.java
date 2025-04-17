package com.example.demo.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.models.Clientes;
import com.example.demo.repositories.ClientesRepository;
import com.example.demo.services.ClientesService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClientesRepository clientesRepository;

    @Override
    public List<Clientes> getAllClientes() {
        // Puedes usar clientesRepository.findAll() o el método personalizado
        return clientesRepository.findByAll();
    }

    @Override
    public Optional<Clientes> getClienteById(Long id) {
        return clientesRepository.findById(id);
    }

    @Override
    public void saveCliente(Clientes cliente) {
        if (Objects.nonNull(cliente)) {
            clientesRepository.save(cliente);
        }
    }

    @Override
    public void deleteCliente(Long id) {
        if (Objects.nonNull(id)) {
            clientesRepository.findById(id).ifPresent(cliente -> clientesRepository.delete(cliente));
        }
    }
}
