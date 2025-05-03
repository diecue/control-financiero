package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap; 
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


import com.example.demo.repositories.BancosRepository; //  (podés quitarlo si no lo usás)
import com.example.demo.repositories.PaisesRepository;
import com.example.demo.repositories.DepartamentosRepository;
import com.example.demo.services.CargaMasivaService;

/**
 * Controlador de las pantallas “Carga masiva de Departamentos” y “Carga masiva de Ciudades”.
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class CargaMasivaController {

    /* ---------- dependencias ---------- */
    private final PaisesRepository        paisRepo;
    private final DepartamentosRepository depRepo;
    private final CargaMasivaService      svc;

    /* ---- (opcional) para pasar bancos como JSON a JS ---- */
    private final BancosRepository        bancosRepo;

    /* =====================================================
       CARGA MASIVA ‑ DEPARTAMENTOS
       ===================================================== */

    @GetMapping("/carga-departamentos")
    public String formDepartamentos(Model model) {

        model.addAttribute("paises", paisRepo.findAll());

        List<Map<String,Object>> bancosJS =
        	    bancosRepo.findAll()
        	              .stream()
        	              .map(b -> {
        	                  Map<String,Object> m = new HashMap<>();
        	                  m.put("id",   b.getId());
        	                  m.put("desc", b.getDescripcion());
        	                  return m;
        	              })
        	              .collect(Collectors.toList());

        	model.addAttribute("bancosJS", bancosJS);

        return "cargaMasiva/departamentos";
    }

    @PostMapping("/carga-departamentos")
    public String saveDepartamentos(@RequestParam Integer idPais,
                                    @RequestParam("nombres") List<String> nombres) {

        svc.guardarDepartamentos(idPais, nombres);
        return "redirect:/carga-departamentos?ok";
    }

    /* =====================================================
       CARGA MASIVA ‑ CIUDADES
       ===================================================== */

    @GetMapping("/carga-ciudades")
    public String formCiudades(Model model) {
        model.addAttribute("departamentos", depRepo.findAll());
        return "cargaMasiva/ciudades";
    }

    @PostMapping("/carga-ciudades")
    public String saveCiudades(@RequestParam Integer idDepartamento,
                               @RequestParam("nombres") List<String> nombres) {

        svc.guardarCiudades(idDepartamento, nombres);
        return "redirect:/carga-ciudades?ok";
    }
}
