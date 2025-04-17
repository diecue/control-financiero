package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Clientes;
import com.example.demo.services.ClientesService;
import com.example.demo.services.BarriosService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value = "/clientes")
@AllArgsConstructor
public class ClientesController {

    private final ClientesService clientesService;
    private final BarriosService barriosService; // Se usará para listar barrios en el formulario

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clientesService.getAllClientes());
        return "clientes/listar";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Clientes());
        model.addAttribute("barrios", barriosService.getAllBarrios());
        return "clientes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Clientes cliente) {
        clientesService.saveCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Clientes> clienteOpt = clientesService.getClienteById(id);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
            model.addAttribute("barrios", barriosService.getAllBarrios());
            return "clientes/formulario";
        }
        return "redirect:/clientes";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {
        clientesService.deleteCliente(id);
        return "redirect:/clientes";
    }
}
