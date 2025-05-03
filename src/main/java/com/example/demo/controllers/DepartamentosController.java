package com.example.demo.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.example.demo.models.Departamentos;
import com.example.demo.services.DepartamentosService;
import com.example.demo.services.PaisesService;

@Controller
@RequestMapping("/departamentos")
@AllArgsConstructor
public class DepartamentosController {

    private final DepartamentosService deptoService;
    private final PaisesService paisesService;

    // Listar
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("departamentos", deptoService.getAllDepartamentos());
        return "Departamentos/listar";
    }

    // Formulario Nuevo
    @GetMapping("/form")
    public String nuevo(Model model) {
        model.addAttribute("departamento", new Departamentos());
        model.addAttribute("paises", paisesService.getAllPaises());
        return "Departamentos/formulario";
    }

    // Guardar
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Departamentos dep) {
        deptoService.saveDepartamento(dep);
        return "redirect:/departamentos";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Departamentos> depOpt = deptoService.getDepartamentoById(id);
        depOpt.ifPresent(d -> model.addAttribute("departamento", d));
        model.addAttribute("paises", paisesService.getAllPaises());
        return "Departamentos/formulario";
    }

    // Eliminar
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        deptoService.deleteDepartamento(id);
        return "redirect:/departamentos";
    }
}
