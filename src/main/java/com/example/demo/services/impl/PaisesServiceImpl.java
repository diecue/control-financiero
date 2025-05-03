package com.example.demo.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.example.demo.models.Paises;
import com.example.demo.repositories.PaisesRepository;
import com.example.demo.services.PaisesService;

@Service
@AllArgsConstructor
public class PaisesServiceImpl implements PaisesService {

    private final PaisesRepository paisesRepository;

    @Override
    public List<Paises> getAllPaises() {
        return paisesRepository.findByAll();
    }

    @Override
    public Optional<Paises> getPaisById(Integer id) {
        return paisesRepository.findById(id);
    }

    @Override
    public void savePais(Paises pais) {
        if (Objects.nonNull(pais)) {
            paisesRepository.save(pais);
        }
    }

    @Override
    public void deletePais(Integer id) {
        paisesRepository.findById(id).ifPresent(paisesRepository::delete);
    }
}
