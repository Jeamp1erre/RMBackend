package com.grupo1.demo.repositories;

import com.grupo1.demo.models.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    List<Tratamiento> findAllByOrderByIdDesc();

    List<Tratamiento> findByDiagnosticoIdOrderByIdDesc(Long diagnosticoId);
}
