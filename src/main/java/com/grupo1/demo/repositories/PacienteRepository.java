package com.grupo1.demo.repositories;

import com.grupo1.demo.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findAllByOrderByIdDesc();

    @Query("SELECT p FROM Paciente p WHERE " +
           "LOWER(CONCAT(p.nombre, ' ', p.apellido)) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(p.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Paciente> searchPaciente(@Param("search") String search);
}
