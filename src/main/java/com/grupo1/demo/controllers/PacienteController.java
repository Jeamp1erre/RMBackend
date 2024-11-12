package com.grupo1.demo.controllers;

import com.grupo1.demo.models.Paciente;
import com.grupo1.demo.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.getAllPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.getPacienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Paciente> searchPaciente(@RequestParam String search) {
        return pacienteService.searchPaciente(search);
    }

    @PostMapping
    public Paciente savePaciente(@RequestBody Paciente paciente) {
        return pacienteService.savePaciente(paciente);
    }
   
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente updatedPaciente) {
        return pacienteService.updatePaciente(id, updatedPaciente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> patchPaciente(@PathVariable Long id, @RequestBody Paciente updatedPaciente) {
        return pacienteService.patchPaciente(id, updatedPaciente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


 @DeleteMapping("/{id}")
public ResponseEntity<Map<String, String>> deletePaciente(@PathVariable Long id) {
    String message = pacienteService.deletePaciente(id);

    if (message.contains("eliminado")) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Paciente eliminado exitosamente");

        return ResponseEntity.ok(response);
    } else {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Paciente no encontrado");

        return ResponseEntity.status(404).body(response);
    }
}

}
