package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Cobros;


//CobrosRepository.java
@Repository
public interface CobrosRepository extends CrudRepository<Cobros,Integer> {
 @Query("select c from Cobros c")
 List<Cobros> findByAll();
}

