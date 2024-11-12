package com.grupo1.demo.services;

import com.grupo1.demo.models.Consulta;
import com.grupo1.demo.models.Paciente;
import com.grupo1.demo.repositories.ConsultaRepository;
import com.grupo1.demo.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAllByOrderByIdDesc();
    }

    public Optional<Consulta> getUltimaConsulta() {
        return consultaRepository.findTopByOrderByIdDesc();
    }

    public List<Consulta> getConsultasByPacienteId(Long pacienteId) {
        return consultaRepository.findByPacienteIdOrderByIdDesc(pacienteId);
    }

    public Consulta createConsulta(Long pacienteId, String motivoConsulta) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(pacienteId);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            Consulta consulta = Consulta.builder()
                    .paciente(paciente)
                    .motivoConsulta(motivoConsulta)
                    .build();
            return consultaRepository.save(consulta);
        }
        return null;  
    }

    public boolean deleteConsulta(Long consultaId) {
        if (consultaRepository.existsById(consultaId)) {
            consultaRepository.deleteById(consultaId);
            return true;
        }
        return false; 
    }

    public Optional<Consulta> updateMotivoConsulta(Long consultaId, String nuevoMotivo) {
        return consultaRepository.findById(consultaId).map(consulta -> {
            consulta.setMotivoConsulta(nuevoMotivo);
            return consultaRepository.save(consulta);
        });
    }
}
