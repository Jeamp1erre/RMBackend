package com.grupo1.demo.services;

import com.grupo1.demo.models.HistoriaMedica;
import com.grupo1.demo.models.Paciente;
import com.grupo1.demo.repositories.HistoriaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaMedicaService {

    @Autowired
    private HistoriaMedicaRepository historiaMedicaRepository;

    @Autowired
    private PacienteService pacienteService;
    

    public HistoriaMedica saveHistoriaMedica(Long pacienteId, HistoriaMedica historiaMedica) {
    Optional<Paciente> pacienteOpt = pacienteService.getPacienteById(pacienteId);
    if (pacienteOpt.isEmpty()) {
        throw new IllegalArgumentException("Paciente no encontrado con el ID: " + pacienteId);
    }
    
    Paciente paciente = pacienteOpt.get();
    historiaMedica.setPaciente(paciente);
    paciente.setHistoriaMedica(historiaMedica);
    
    return historiaMedicaRepository.save(historiaMedica);
}

    public Optional<HistoriaMedica> updateHistoriaMedica(Long id, HistoriaMedica newHistoriaMedica) {
        return historiaMedicaRepository.findById(id).map(existingHistoria -> {
            if (newHistoriaMedica.getAntecedentesMedicos() != null) 
                existingHistoria.setAntecedentesMedicos(newHistoriaMedica.getAntecedentesMedicos());
            if (newHistoriaMedica.getCirugiasAnteriores() != null) 
                existingHistoria.setCirugiasAnteriores(newHistoriaMedica.getCirugiasAnteriores());
            if (newHistoriaMedica.getAlergias() != null) 
                existingHistoria.setAlergias(newHistoriaMedica.getAlergias());
            if (newHistoriaMedica.getAntecedentesFamiliares() != null) 
                existingHistoria.setAntecedentesFamiliares(newHistoriaMedica.getAntecedentesFamiliares());
            if (newHistoriaMedica.getPaciente() != null) 
                existingHistoria.setPaciente(newHistoriaMedica.getPaciente());

            return historiaMedicaRepository.save(existingHistoria);
        });
    }

    public Optional<HistoriaMedica> findByPacienteId(Long pacienteId) {
        return historiaMedicaRepository.findByPacienteId(pacienteId);
    }

public Optional<HistoriaMedica> clearHistoriaMedica(Long id) {
    return historiaMedicaRepository.findById(id).map(historia -> {
        if (historia.getAntecedentesMedicos() != null && !historia.getAntecedentesMedicos().isEmpty()) {
            historia.setAntecedentesMedicos(List.of());  
        }
        if (historia.getCirugiasAnteriores() != null && !historia.getCirugiasAnteriores().isEmpty()) {
            historia.setCirugiasAnteriores(List.of()); 
        }
        if (historia.getAlergias() != null && !historia.getAlergias().isEmpty()) {
            historia.setAlergias(List.of());  
        }
        if (historia.getAntecedentesFamiliares() != null && !historia.getAntecedentesFamiliares().isEmpty()) {
            historia.setAntecedentesFamiliares(List.of());  
        }
        
        return historiaMedicaRepository.save(historia);
    });
}


    public Optional<HistoriaMedica> findById(Long id) {
        return historiaMedicaRepository.findById(id);
    }

    
}
