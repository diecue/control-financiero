package com.example.demo.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.models.Paises;
import com.example.demo.models.Departamentos;
import com.example.demo.models.Ciudades;
import com.example.demo.repositories.PaisesRepository;
import com.example.demo.repositories.DepartamentosRepository;
import com.example.demo.repositories.CiudadesRepository;

/**
 * Servicio utilitario para “carga masiva” de Departamentos y Ciudades.
 */
@Service
@RequiredArgsConstructor
public class CargaMasivaService {

    private final PaisesRepository        paisRepo;
    private final DepartamentosRepository depRepo;
    private final CiudadesRepository      ciuRepo;

    /* ==========================================================
       DEPARTAMENTOS
       ========================================================== */

    /**
     * Persiste de una sola vez todos los departamentos del país indicado.
     *
     * @param idPais   ID del país
     * @param nombres  Lista de descripciones (una por fila del formulario)
     */
    public void guardarDepartamentos(Integer idPais, List<String> nombres) {

        Paises pais = paisRepo.findById(idPais)
                              .orElseThrow(() -> new IllegalArgumentException("País no encontrado"));

        List<Departamentos> lista =
            nombres.stream()
                   .map(String::trim)
                   .filter(s -> !s.isBlank())
                   .map(desc -> {
                       Departamentos d = new Departamentos();
                       d.setPais(pais);
                       d.setDescripcion(desc);
                       return d;
                   })
                   .collect(Collectors.toList());

        depRepo.saveAll(lista);     // ✔ salva en un solo “batch”
    }

    /* ==========================================================
       CIUDADES
       ========================================================== */

    /**
     * Persiste de una sola vez todas las ciudades del departamento indicado.
     *
     * @param idDepartamento  ID del departamento
     * @param nombres         Lista de descripciones
     */
    public void guardarCiudades(Integer idDepartamento, List<String> nombres) {

        Departamentos dep = depRepo.findById(idDepartamento)
                                   .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));

        List<Ciudades> lista =
            nombres.stream()
                   .map(String::trim)
                   .filter(s -> !s.isBlank())
                   .map(desc -> {
                       Ciudades c = new Ciudades();
                       c.setDepartamento(dep);
                       c.setDescripcion(desc);
                       return c;
                   })
                   .collect(Collectors.toList());

        ciuRepo.saveAll(lista);
    }
}
