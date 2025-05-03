package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Bancos;


//BancosRepository.java
@Repository
public interface BancosRepository extends CrudRepository<Bancos,Integer> {
 List<Bancos> findAll();
}

