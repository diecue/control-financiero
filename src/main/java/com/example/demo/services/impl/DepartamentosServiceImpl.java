package com.example.demo.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.example.demo.models.Departamentos;
import com.example.demo.repositories.DepartamentosRepository;
import com.example.demo.services.DepartamentosService;

@Service
@AllArgsConstructor
public class DepartamentosServiceImpl implements DepartamentosService {

    private final DepartamentosRepository departamentosRepository;

    @Override
    public List<Departamentos> getAllDepartamentos() {
        return departamentosRepository.findByAll();
    }

    @Override
    public Optional<Departamentos> getDepartamentoById(Integer id) {
        return departamentosRepository.findById(id);
    }

    @Override
    public void saveDepartamento(Departamentos dep) {
        if (Objects.nonNull(dep)) {
            departamentosRepository.save(dep);
        }
    }

    @Override
    public void deleteDepartamento(Integer id) {
        departamentosRepository.findById(id).ifPresent(departamentosRepository::delete);
    }
}
