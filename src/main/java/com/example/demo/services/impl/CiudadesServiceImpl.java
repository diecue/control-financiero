package com.example.demo.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.models.Ciudades;
import com.example.demo.repositories.CiudadesRepository;
import com.example.demo.services.CiudadesService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CiudadesServiceImpl implements CiudadesService {

    private final CiudadesRepository ciudadesRepository;

    @Override
    public List<Ciudades> getAllCiudades() {
        return ciudadesRepository.findByAll();
    }
}
