package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import com.example.demo.models.Bancos;
import com.example.demo.models.Cobros;
import com.example.demo.repositories.*;
import com.example.demo.services.*;

@Controller
@RequestMapping("/cobros")
@AllArgsConstructor
public class CobrosController {

    /* ---------- dependencias ---------- */
    private final CobrosService      cobrosService;
    private final TipoPagosRepository tipoPagosRepo;
    private final TarjetasRepository  tarjetasRepo;
    private final ChequesRepository   chequesRepo;
    private final BancosRepository    bancosRepo;
    private final ClientesRepository  clientesRepo;
    private final FacturasRepository  facturasRepo;

    /* ---------- listado ---------- */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cobros", cobrosService.getAllCobros());
        return "Cobros/listar";
    }

    /* ---------- nuevo ---------- */
    @GetMapping("/form")
    public String nuevo(Model model) {
        model.addAttribute("cobro", new Cobros());
        cargarCombos(model);
        return "Cobros/formulario";
    }

    /* ---------- editar ---------- */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Cobros> cobroOpt = cobrosService.getCobroById(id);
        cobroOpt.ifPresent(c -> model.addAttribute("cobro", c));
        cargarCombos(model);
        return "Cobros/formulario";
    }

    /* ---------- guardar ---------- */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cobros cobro) {

        /* Garantizar que solo quede el campo correcto */
        int tp = cobro.getTipoPago().getId();   // 1=Efectivo, 2=Tarjeta, 3=Cheque

        switch (tp) {
            case 1 -> {                // EFECTIVO
                cobro.setTarjeta(null);
                cobro.setCheque(null);
            }
            case 2 -> {                // TARJETA
                cobro.setBanco(null);
                cobro.setCheque(null);
            }
            case 3 -> {                // CHEQUE
                cobro.setBanco(null);
                cobro.setTarjeta(null);
            }
        }

        cobrosService.saveCobro(cobro);
        return "redirect:/cobros";
    }


    /* ---------- eliminar ---------- */
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        cobrosService.deleteCobro(id);
        return "redirect:/cobros";
    }

    /* ================================================================ */
    /** Carga todos los combos y el array JSON de bancos para el JS  */
    private void cargarCombos(Model model) {

        /* catálogos para los &lt;select&gt; */
        model.addAttribute("tiposPago", tipoPagosRepo.findAll());
        model.addAttribute("tarjetas",  tarjetasRepo.findAll());
        model.addAttribute("cheques",   chequesRepo.findAll());
        model.addAttribute("bancos",    bancosRepo.findAll());
        model.addAttribute("clientes",  clientesRepo.findAll());

        /* ---- bancosJS →  [{id:1,desc:'Banco Itau'}, …] --------------- */
        List<Map<String,Object>> bancosJS =
            bancosRepo.findAll()
                      .stream()
                      .map(this::bankToJson)
                      .collect(Collectors.toList());

        model.addAttribute("bancosJS", bancosJS);
    }

    /** Convierte un Banco en Map para el array JS */
    private Map<String,Object> bankToJson(Bancos b) {
        return Map.of("id", b.getId(), "desc", b.getDescripcion());
    }
}
