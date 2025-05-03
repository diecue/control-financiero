package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.TipoPagos;


//TipoPagosRepository.java
@Repository
public interface TipoPagosRepository extends CrudRepository<TipoPagos,Integer> {
 List<TipoPagos> findAll();
}

