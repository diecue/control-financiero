package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.models.Cobros;
import com.example.demo.repositories.CobrosRepository;
import com.example.demo.services.CobrosService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CobrosServiceImpl implements CobrosService {

    private final CobrosRepository CobrosRepository;

    @Override public List<Cobros> getAllCobros()   { return CobrosRepository.findByAll(); }
    @Override public Optional<Cobros> getCobroById(Integer id){ return CobrosRepository.findById(id);}
    @Override public void saveCobro(Cobros c)      { CobrosRepository.save(c); }
    @Override public void deleteCobro(Integer id)  { CobrosRepository.deleteById(id); }
}
