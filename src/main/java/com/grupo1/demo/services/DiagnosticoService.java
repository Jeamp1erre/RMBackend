package com.grupo1.demo.services;

import com.grupo1.demo.models.Diagnostico;
import com.grupo1.demo.models.Consulta;
import com.grupo1.demo.repositories.DiagnosticoRepository;
import com.grupo1.demo.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoRepository.findAllByOrderByIdDesc();
    }

    public List<Diagnostico> getDiagnosticosByConsultaId(Long consultaId) {
        return diagnosticoRepository.findByConsultaIdOrderByIdDesc(consultaId);
    }

    public Diagnostico createDiagnostico(Long consultaId, String nombreDoctor, String descripcionDiagnostico) {
        Optional<Consulta> consultaOpt = consultaRepository.findById(consultaId);
        if (consultaOpt.isPresent()) {
            Consulta consulta = consultaOpt.get();
            Diagnostico diagnostico = Diagnostico.builder()
                    .consulta(consulta)
                    .nombreDoctor(nombreDoctor)
                    .descripcionDiagnostico(descripcionDiagnostico)
                    .build();
            return diagnosticoRepository.save(diagnostico);
        }
        return null; 
    }

    public boolean deleteDiagnostico(Long diagnosticoId) {
        if (diagnosticoRepository.existsById(diagnosticoId)) {
            diagnosticoRepository.deleteById(diagnosticoId);
            return true;
        }
        return false; 
    }

    public Optional<Diagnostico> updateDiagnostico(Long diagnosticoId, String nombreDoctor, String descripcionDiagnostico) {
        return diagnosticoRepository.findById(diagnosticoId).map(diagnostico -> {
            if (nombreDoctor != null && !nombreDoctor.isEmpty()) {
                diagnostico.setNombreDoctor(nombreDoctor);
            }
            if (descripcionDiagnostico != null && !descripcionDiagnostico.isEmpty()) {
                diagnostico.setDescripcionDiagnostico(descripcionDiagnostico);
            }
            return diagnosticoRepository.save(diagnostico);
        });
    }
}
