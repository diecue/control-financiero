// TarjetasServiceImpl.java
package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.example.demo.models.Cheques;
import com.example.demo.repositories.ChequesRepository;
import com.example.demo.services.ChequesService;

//ChequesServiceImpl.java
@Service @AllArgsConstructor
public class ChequesServiceImpl implements ChequesService {

 private final ChequesRepository repo;

 public List<Cheques> getAllCheques()              { return repo.findAll(); }
 public Optional<Cheques> getChequeById(Integer id){ return repo.findById(id);}
 public void saveCheque(Cheques c)                 { repo.save(c); }
 public void deleteCheque(Integer id)              { repo.deleteById(id); }
}
