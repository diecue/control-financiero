package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Clientes;

@Repository
public interface ClientesRepository extends CrudRepository<Clientes, Long> {

    // Opción 1: Método personalizado similar a RolesRepository
    @Query("select c from Clientes c")
    List<Clientes> findByAll();
    
    // Alternativamente, puedes usar directamente findAll() de JpaRepository
}
