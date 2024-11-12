package com.grupo1.demo.controllers;

import com.grupo1.demo.models.Diagnostico;
import com.grupo1.demo.services.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping
    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoService.getAllDiagnosticos();
    }

    @GetMapping("/consulta/{consultaId}")
    public List<Diagnostico> getDiagnosticosByConsultaId(@PathVariable Long consultaId) {
        return diagnosticoService.getDiagnosticosByConsultaId(consultaId);
    }

    @PostMapping("/{consultaId}")
    public ResponseEntity<Diagnostico> createDiagnostico(
            @PathVariable Long consultaId,
            @RequestParam String nombreDoctor,
            @RequestParam String descripcionDiagnostico) {
        Diagnostico nuevoDiagnostico = diagnosticoService.createDiagnostico(consultaId, nombreDoctor, descripcionDiagnostico);
        return nuevoDiagnostico != null ? ResponseEntity.ok(nuevoDiagnostico) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{diagnosticoId}")
    public ResponseEntity<String> deleteDiagnostico(@PathVariable Long diagnosticoId) {
        if (diagnosticoService.deleteDiagnostico(diagnosticoId)) {
            return ResponseEntity.ok("Diagnóstico eliminado exitosamente.");
        } else {
            return ResponseEntity.status(404).body("Diagnóstico no encontrado.");
        }
    }

    @PatchMapping("/{diagnosticoId}")
    public ResponseEntity<Diagnostico> updateDiagnostico(
            @PathVariable Long diagnosticoId,
            @RequestParam(required = false) String nombreDoctor,
            @RequestParam(required = false) String descripcionDiagnostico) {
        Optional<Diagnostico> diagnosticoActualizado = diagnosticoService.updateDiagnostico(diagnosticoId, nombreDoctor, descripcionDiagnostico);
        return diagnosticoActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
