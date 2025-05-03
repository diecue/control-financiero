package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.Cobros;

public interface CobrosService {
    List<Cobros> getAllCobros();
    Optional<Cobros> getCobroById(Integer id);
    void saveCobro(Cobros c);
    void deleteCobro(Integer id);
}
