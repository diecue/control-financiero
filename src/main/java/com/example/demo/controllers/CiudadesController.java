package com.example.demo.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.example.demo.models.Ciudades;
import com.example.demo.services.CiudadesService;
import com.example.demo.services.DepartamentosService;

@Controller
@RequestMapping("/ciudades")
@AllArgsConstructor
public class CiudadesController {

    private final CiudadesService ciudadesService;
    private final DepartamentosService departamentosService;

    // Listar
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ciudades", ciudadesService.getAllCiudades());
        return "Ciudades/listar";
    }

    // Formulario nuevo
    @GetMapping("/form")
    public String nuevo(Model model) {
        model.addAttribute("ciudad", new Ciudades());
        model.addAttribute("departamentos", departamentosService.getAllDepartamentos());
        return "Ciudades/formulario";
    }

    // Guardar
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Ciudades ciudad) {
        ciudadesService.saveCiudad(ciudad);
        return "redirect:/ciudades";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Ciudades> ciudadOpt = ciudadesService.getCiudadById(id);
        ciudadOpt.ifPresent(c -> model.addAttribute("ciudad", c));
        model.addAttribute("departamentos", departamentosService.getAllDepartamentos());
        return "Ciudades/formulario";
    }

    // Eliminar
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        ciudadesService.deleteCiudad(id);
        return "redirect:/ciudades";
    }
}
