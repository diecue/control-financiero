package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Cheques;


//ChequesRepository.java
@Repository
public interface ChequesRepository extends CrudRepository<Cheques,Integer> {
 List<Cheques> findAll();
}


