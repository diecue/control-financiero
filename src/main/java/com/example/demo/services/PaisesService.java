package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.Paises;

public interface PaisesService {

    List<Paises> getAllPaises();
    Optional<Paises> getPaisById(Integer id);
    void savePais(Paises pais);
    void deletePais(Integer id);
}
