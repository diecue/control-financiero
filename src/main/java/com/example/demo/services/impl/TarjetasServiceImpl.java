// TarjetasServiceImpl.java
package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.example.demo.models.Tarjetas;
import com.example.demo.repositories.TarjetasRepository;
import com.example.demo.services.TarjetasService;

@Service
@AllArgsConstructor
public class TarjetasServiceImpl implements TarjetasService {

    private final TarjetasRepository repo;

    public List<Tarjetas> getAllTarjetas()               { return repo.findAll(); }
    public Optional<Tarjetas> getTarjetaById(Integer id) { return repo.findById(id);}
    public void saveTarjeta(Tarjetas t)                  { repo.save(t); }
    public void deleteTarjeta(Integer id)                { repo.deleteById(id); }
}
