// BancosService.java
package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.Bancos;

public interface BancosService {
    List<Bancos> getAllBancos();
    Optional<Bancos> getBancoById(Integer id);
    void saveBanco(Bancos b);
    void deleteBanco(Integer id);
}
