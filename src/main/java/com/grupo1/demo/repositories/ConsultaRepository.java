package com.grupo1.demo.repositories;

import com.grupo1.demo.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findAllByOrderByIdDesc();

    @Query("SELECT c FROM Consulta c ORDER BY c.id DESC")
    Optional<Consulta> findTopByOrderByIdDesc();

    List<Consulta> findByPacienteIdOrderByIdDesc(Long pacienteId);
}
