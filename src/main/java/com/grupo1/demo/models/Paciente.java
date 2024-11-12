package com.grupo1.demo.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;  
    private LocalDate fechaNacimiento;
    private String genero;
    private String telefono;
    private String email;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonManagedReference("PacienteHistoria")  
    private HistoriaMedica historiaMedica;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonManagedReference("PacienteConsulta")
    private List<Consulta> consulta = new ArrayList<>();
}
