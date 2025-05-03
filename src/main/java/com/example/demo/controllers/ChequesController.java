package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Cheques;
import com.example.demo.models.Bancos;
import com.example.demo.services.ChequesService;
import com.example.demo.repositories.BancosRepository;

import lombok.AllArgsConstructor;
import java.util.Map;  
import java.time.LocalDate;
import java.math.BigDecimal;

@Controller
@RequestMapping("/cheques")
@AllArgsConstructor
public class ChequesController {

    private final ChequesService chequesService;
    private final BancosRepository bancosRepo;

    /* Web */
    @GetMapping
    public String listar(Model model){
        model.addAttribute("cheques", chequesService.getAllCheques());
        return "Cheques/listar";
    }

    @GetMapping("/form")
    public String nuevo(Model model){
        model.addAttribute("cheque", new Cheques());
        model.addAttribute("bancos", bancosRepo.findAll());
        return "Cheques/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){
        chequesService.getChequeById(id)
                       .ifPresent(c -> model.addAttribute("cheque", c));
        model.addAttribute("bancos", bancosRepo.findAll());
        return "Cheques/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cheques cheque){
        chequesService.saveCheque(cheque);
        return "redirect:/cheques";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id){
        chequesService.deleteCheque(id);
        return "redirect:/cheques";
    }

    /* -------------------------------------------------
    ChequesController.java  (fragmento)
 --------------------------------------------------*/
 @PostMapping(value = "/api",
              consumes = "application/json",
              produces = "application/json")
 @ResponseBody
 public Map<String,Object> guardarApi(@RequestBody Map<String,String> json) {

     /* Campos que vienen del popup */
     Integer bancoId      = Integer.valueOf(json.get("bancoId"));
     String  numeroCheque = json.get("numeroCheque");
     String  titular      = json.get("titular");
     BigDecimal monto = new BigDecimal( json.get("monto") ); 
     LocalDate fechaEmi   = LocalDate.parse(json.get("fechaEmision"));
     LocalDate fechaPag   = LocalDate.parse(json.get("fechaPago"));

     /* Banco obligatorio */
     Bancos banco = bancosRepo.findById(bancoId)
                       .orElseThrow(() -> new RuntimeException("Banco inexistente"));

     /* Construir y guardar el Cheque */
     Cheques ch = new Cheques();
     ch.setBanco(banco);
     ch.setNumeroCheque(numeroCheque);
     ch.setTitular(titular);
     ch.setMonto(monto);
     ch.setFechaEmision(fechaEmi);
     ch.setFechaPago(fechaPag);

     chequesService.saveCheque(ch);

     /* Respuesta mínima para el combo */
     String label = numeroCheque + " - " + titular;
     return Map.of("id", ch.getId(), "label", label);
 }

}
