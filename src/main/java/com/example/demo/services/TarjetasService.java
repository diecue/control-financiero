// TarjetasService.java
package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.Tarjetas;

public interface TarjetasService {
    List<Tarjetas> getAllTarjetas();
    Optional<Tarjetas> getTarjetaById(Integer id);
    void saveTarjeta(Tarjetas t);
    void deleteTarjeta(Integer id);
}
