package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.Departamentos;

public interface DepartamentosService {

    List<Departamentos> getAllDepartamentos();
    Optional<Departamentos> getDepartamentoById(Integer id);
    void saveDepartamento(Departamentos dep);
    void deleteDepartamento(Integer id);
}
