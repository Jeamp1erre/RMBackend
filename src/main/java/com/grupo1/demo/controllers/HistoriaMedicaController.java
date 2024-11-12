package com.grupo1.demo.controllers;

import com.grupo1.demo.models.HistoriaMedica;
import com.grupo1.demo.services.HistoriaMedicaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/historia-medica")
public class HistoriaMedicaController {

    @Autowired
    private HistoriaMedicaService historiaMedicaService;
  


    @PostMapping("/{pacienteId}")
    public ResponseEntity<HistoriaMedica> createHistoriaMedica(
            @PathVariable Long pacienteId,
            @RequestBody HistoriaMedica historiaMedica) {
        
        try {
            HistoriaMedica savedHistoria = historiaMedicaService.saveHistoriaMedica(pacienteId, historiaMedica);
            return new ResponseEntity<>(savedHistoria, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<HistoriaMedica> updateHistoriaMedica(
            @PathVariable Long id,
            @RequestBody HistoriaMedica newHistoriaMedica) {

        Optional<HistoriaMedica> updatedHistoria = historiaMedicaService.updateHistoriaMedica(id, newHistoriaMedica);

        return updatedHistoria.map(historia ->
                new ResponseEntity<>(historia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

  @GetMapping("/paciente/{pacienteId}")
public ResponseEntity<HistoriaMedica> getHistoriaMedicaByPacienteId(@PathVariable Long pacienteId) {
    Optional<HistoriaMedica> historiaMedica = historiaMedicaService.findByPacienteId(pacienteId);
    
    if (historiaMedica.isEmpty()) {
        HistoriaMedica nuevaHistoriaMedica = new HistoriaMedica();
        nuevaHistoriaMedica.setAntecedentesMedicos(new ArrayList<>());
        nuevaHistoriaMedica.setCirugiasAnteriores(new ArrayList<>());
        nuevaHistoriaMedica.setAlergias(new ArrayList<>());
        nuevaHistoriaMedica.setAntecedentesFamiliares(new ArrayList<>());
        
        nuevaHistoriaMedica = historiaMedicaService.saveHistoriaMedica(pacienteId, nuevaHistoriaMedica);
        
        return new ResponseEntity<>(nuevaHistoriaMedica, HttpStatus.CREATED);
    }

    return new ResponseEntity<>(historiaMedica.get(), HttpStatus.OK);
}


    @PatchMapping("/clear/{id}")
    public ResponseEntity<HistoriaMedica> clearHistoriaMedica(@PathVariable Long id) {
        Optional<HistoriaMedica> clearedHistoria = historiaMedicaService.clearHistoriaMedica(id);
        return clearedHistoria.map(historia ->
                new ResponseEntity<>(historia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public HistoriaMedica getHistoriaMedicaById(@PathVariable Long id) {
        Optional<HistoriaMedica> historiaMedica = historiaMedicaService.findById(id);
        if (historiaMedica.isPresent()) {
            return historiaMedica.get();
        } else {
            throw new RuntimeException("Historia médica no encontrada con ID: " + id);
        }
    }
}
