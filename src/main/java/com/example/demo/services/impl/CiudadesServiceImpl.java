package com.example.demo.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.example.demo.models.Ciudades;
import com.example.demo.repositories.CiudadesRepository;
import com.example.demo.services.CiudadesService;

@Service
@AllArgsConstructor
public class CiudadesServiceImpl implements CiudadesService {

    private final CiudadesRepository ciudadesRepository;

    @Override
    public List<Ciudades> getAllCiudades() {
        return ciudadesRepository.findByAll();
    }

    @Override
    public Optional<Ciudades> getCiudadById(Integer id) {
        return ciudadesRepository.findById(id);
    }

    @Override
    public void saveCiudad(Ciudades ciudad) {
        if (Objects.nonNull(ciudad)) {
            ciudadesRepository.save(ciudad);
        }
    }

    @Override
    public void deleteCiudad(Integer id) {
        ciudadesRepository.findById(id).ifPresent(ciudadesRepository::delete);
    }
}
