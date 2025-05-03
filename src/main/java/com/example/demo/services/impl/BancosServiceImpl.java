// TarjetasServiceImpl.java
package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.example.demo.models.Bancos;
import com.example.demo.repositories.BancosRepository;
import com.example.demo.services.BancosService;

@Service @AllArgsConstructor
public class BancosServiceImpl implements BancosService {

    private final BancosRepository repo;

    public List<Bancos> getAllBancos()               { return repo.findAll(); }
    public Optional<Bancos> getBancoById(Integer id) { return repo.findById(id); }
    public void saveBanco(Bancos b)                  { repo.save(b); }
    public void deleteBanco(Integer id)              { repo.deleteById(id); }
}
