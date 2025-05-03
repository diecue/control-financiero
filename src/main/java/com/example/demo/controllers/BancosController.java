package com.example.demo.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import com.example.demo.models.Bancos;
import com.example.demo.services.BancosService;

@Controller
@RequestMapping("/bancos")
@AllArgsConstructor
public class BancosController {

    private final BancosService bancosService;

    /* ---------- Listado ---------- */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bancos", bancosService.getAllBancos());
        return "Bancos/listar";
    }

    /* ---------- Formulario Nuevo ---------- */
    @GetMapping("/form")
    public String nuevo(Model model) {
        model.addAttribute("banco", new Bancos());
        return "Bancos/formulario";
    }

    /* ---------- Guardar (nuevo | editado) ---------- */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Bancos banco) {
        bancosService.saveBanco(banco);
        return "redirect:/bancos";
    }

    /* ---------- Editar ---------- */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Bancos> bancoOpt = bancosService.getBancoById(id);
        bancoOpt.ifPresent(b -> model.addAttribute("banco", b));
        return "Bancos/formulario";
    }

    /* ---------- Eliminar ---------- */
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        bancosService.deleteBanco(id);
        return "redirect:/bancos";
    }
}
