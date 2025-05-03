package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Departamentos;

@Repository
public interface DepartamentosRepository extends CrudRepository<Departamentos, Integer> {

    @Query("select d from Departamentos d")
    List<Departamentos> findByAll();
}
