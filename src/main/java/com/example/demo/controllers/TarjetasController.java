package com.example.demo.controllers;

import java.util.Optional;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import com.example.demo.models.Tarjetas;
import com.example.demo.models.Bancos;
import com.example.demo.repositories.BancosRepository;
import com.example.demo.services.TarjetasService;
import java.time.LocalDate;

@Controller
@RequestMapping("/tarjetas")
@AllArgsConstructor
public class TarjetasController {

    private final TarjetasService tarjetasService;
    private final BancosRepository bancosRepo;

    /* ---------- web ---------- */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tarjetas", tarjetasService.getAllTarjetas());
        return "Tarjetas/listar";
    }

    @GetMapping("/form")
    public String nuevo(Model model) {
        model.addAttribute("tarjeta", new Tarjetas());
        model.addAttribute("bancos", bancosRepo.findAll());
        return "Tarjetas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Tarjetas> t = tarjetasService.getTarjetaById(id);
        t.ifPresent(x -> model.addAttribute("tarjeta", x));
        model.addAttribute("bancos", bancosRepo.findAll());
        return "Tarjetas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Tarjetas tarjeta) {
        tarjetasService.saveTarjeta(tarjeta);
        return "redirect:/tarjetas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        tarjetasService.deleteTarjeta(id);
        return "redirect:/tarjetas";
    }

    @PostMapping(value = "/api", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String,Object> guardarApi(@RequestBody Map<String,String> json){
			
		/* 1)  Datos del front */
		String nombre  = json.get("nombreTarjeta");
		String numero  = json.get("numeroEnmascarado");
		String titular = json.get("titular");
		String venc    = json.get("fechaVenc");
		Integer bancoId= Integer.valueOf( json.get("bancoId") );   // ← nuevo
		
		/* 2)  Instanciar y poblar */
		Tarjetas t = new Tarjetas();
		t.setNombreTarjeta(nombre);
		t.setNumeroEnmascarado(numero);
		t.setTitular(titular);
		if(venc != null && !venc.isBlank())
		t.setFechaVenc(LocalDate.parse(venc));
		
		/* 3)  Banco obligatorio */
		Bancos banco = bancosRepo.findById(bancoId)
		               .orElseThrow(()->new RuntimeException("Banco no encontrado"));
		t.setBanco(banco);
		
		/* 4)  Guardar */
		tarjetasService.saveTarjeta(t);
		
		/* 5)  Respuesta para el combo */
		String label = nombre + " (" + numero + ")";
		return Map.of("id", t.getId(), "label", label);		
    }

}
