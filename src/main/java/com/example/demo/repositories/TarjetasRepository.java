package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Tarjetas;


//TarjetasRepository.java
@Repository
public interface TarjetasRepository extends CrudRepository<Tarjetas,Integer> {
 List<Tarjetas> findAll();
}

