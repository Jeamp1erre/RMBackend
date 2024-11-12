package com.grupo1.demo.services;

import com.grupo1.demo.models.Paciente;
import com.grupo1.demo.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAllByOrderByIdDesc();
    }

    public Optional<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }


    public List<Paciente> searchPaciente(String search) {
        return pacienteRepository.searchPaciente(search);
    }

    
    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }


    public String deletePaciente(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isPresent()) {
            pacienteRepository.deleteById(id);
            return "Paciente con ID " + id + " eliminado correctamente.";
        } else {
            return "Paciente con ID " + id + " no encontrado.";
        }
    }
 
    public Optional<Paciente> updatePaciente(Long id, Paciente updatedPaciente) {
        return pacienteRepository.findById(id).map(existingPaciente -> {
            existingPaciente.setNombre(updatedPaciente.getNombre());
            existingPaciente.setApellido(updatedPaciente.getApellido());
            existingPaciente.setDni(updatedPaciente.getDni());
            existingPaciente.setFechaNacimiento(updatedPaciente.getFechaNacimiento());
            existingPaciente.setGenero(updatedPaciente.getGenero());
            existingPaciente.setTelefono(updatedPaciente.getTelefono());
            existingPaciente.setEmail(updatedPaciente.getEmail());
            return pacienteRepository.save(existingPaciente);
        });
    }

    public Optional<Paciente> patchPaciente(Long id, Paciente updatedPaciente) {
        return pacienteRepository.findById(id).map(existingPaciente -> {
            if (updatedPaciente.getNombre() != null) existingPaciente.setNombre(updatedPaciente.getNombre());
            if (updatedPaciente.getApellido() != null) existingPaciente.setApellido(updatedPaciente.getApellido());
            if (updatedPaciente.getDni() != null) existingPaciente.setDni(updatedPaciente.getDni());
            if (updatedPaciente.getFechaNacimiento() != null) existingPaciente.setFechaNacimiento(updatedPaciente.getFechaNacimiento());
            if (updatedPaciente.getGenero() != null) existingPaciente.setGenero(updatedPaciente.getGenero());
            if (updatedPaciente.getTelefono() != null) existingPaciente.setTelefono(updatedPaciente.getTelefono());
            if (updatedPaciente.getEmail() != null) existingPaciente.setEmail(updatedPaciente.getEmail());
            return pacienteRepository.save(existingPaciente);
        });
    }
}
