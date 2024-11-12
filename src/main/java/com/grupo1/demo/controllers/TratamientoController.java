package com.grupo1.demo.controllers;

import com.grupo1.demo.models.Tratamiento;
import com.grupo1.demo.services.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    @GetMapping
    public List<Tratamiento> getAllTratamientos() {
        return tratamientoService.getAllTratamientos();  
    }

    @GetMapping("/diagnostico/{diagnosticoId}")
    public List<Tratamiento> getTratamientosByDiagnosticoId(@PathVariable Long diagnosticoId) {
        return tratamientoService.getTratamientosByDiagnosticoId(diagnosticoId);  
    }

    @PostMapping("/diagnostico/{diagnosticoId}")
    public ResponseEntity<Tratamiento> createTratamiento(
            @PathVariable Long diagnosticoId, 
            @RequestParam String descripcionTratamiento, 
            @RequestParam Integer duracionDias) {
        
        Tratamiento tratamiento = tratamientoService.createTratamiento(diagnosticoId, descripcionTratamiento, duracionDias);
        
        if (tratamiento != null) {
            return ResponseEntity.ok(tratamiento);  
        }
        
        return ResponseEntity.notFound().build();  
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tratamiento> updateTratamiento(
            @PathVariable Long id, 
            @RequestBody Tratamiento updatedTratamiento) {
        
        Optional<Tratamiento> existingTratamiento = tratamientoService.updateTratamiento(id, updatedTratamiento);
        
        return existingTratamiento
                .map(ResponseEntity::ok)  
                .orElseGet(() -> ResponseEntity.notFound().build());  
    }

    // Eliminar un tratamiento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTratamiento(@PathVariable Long id) {
        boolean deleted = tratamientoService.deleteTratamiento(id);
        
        if (deleted) {
            return ResponseEntity.noContent().build();  
        }
        
        return ResponseEntity.notFound().build();  
    }
}
