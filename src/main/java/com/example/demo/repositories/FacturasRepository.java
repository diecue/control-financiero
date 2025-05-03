package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Facturas;


//FacturasRepository.java
@Repository
public interface FacturasRepository extends CrudRepository<Facturas,Integer> {
 List<Facturas> findAll();
}

