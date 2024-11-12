package com.grupo1.demo.repositories;

import com.grupo1.demo.models.HistoriaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoriaMedicaRepository extends JpaRepository<HistoriaMedica, Long> {
   
    Optional<HistoriaMedica> findByPacienteId(Long pacienteId);
    Optional<HistoriaMedica> findById(Long id);
}
