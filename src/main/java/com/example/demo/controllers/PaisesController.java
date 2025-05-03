package com.example.demo.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.example.demo.models.Paises;
import com.example.demo.services.PaisesService;

@Controller
@RequestMapping("/paises")
@AllArgsConstructor
public class PaisesController {

    private final PaisesService paisesService;

    // Listar todos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("paises", paisesService.getAllPaises());
        return "Paises/listar";
    }

    // Formulario Nuevo
    @GetMapping("/form")
    public String nuevo(Model model) {
        model.addAttribute("pais", new Paises());
        return "Paises/formulario";
    }

    // Guardar (nuevo o editado)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Paises pais) {
        paisesService.savePais(pais);
        return "redirect:/paises";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Paises> paisOpt = paisesService.getPaisById(id);
        paisOpt.ifPresent(pais -> model.addAttribute("pais", pais));
        return "Paises/formulario";
    }

    // Eliminar
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        paisesService.deletePais(id);
        return "redirect:/paises";
    }
}
