package com.grupo1.demo.repositories;

import com.grupo1.demo.models.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

    List<Diagnostico> findAllByOrderByIdDesc();

    List<Diagnostico> findByConsultaIdOrderByIdDesc(Long consultaId);
}
