package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Barrios;
import com.example.demo.services.BarriosService;
import com.example.demo.services.CiudadesService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/barrios")
@AllArgsConstructor
public class BarriosController {

    private final BarriosService barriosService;

    private final CiudadesService ciudadesService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("barrios", barriosService.getAllBarrios());
        return "barrios/listar";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
    	System.out.println("Entrando al método formulario");
        model.addAttribute("barrio", new Barrios());
        model.addAttribute("ciudades", ciudadesService.getAllCiudades());
       
        return "barrios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Barrios barrio) {
        barriosService.saveBarrio(barrio);
        return "redirect:/barrios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Barrios> barrioOpt = barriosService.getBarrioById(id);
        if (barrioOpt.isPresent()) {
            model.addAttribute("barrio", barrioOpt.get());
         // Cargar la lista de ciudades para el formulario de edición
            model.addAttribute("ciudades", ciudadesService.getAllCiudades());
            return "barrios/formulario";
        }
        return "redirect:/barrios";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        barriosService.deleteBarrio(id);
        return "redirect:/barrios";
    }
}
