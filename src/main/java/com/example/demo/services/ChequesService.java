// ChequesService.java
package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.Cheques;

public interface ChequesService {
    List<Cheques> getAllCheques();
    Optional<Cheques> getChequeById(Integer id);
    void saveCheque(Cheques c);
    void deleteCheque(Integer id);
}
