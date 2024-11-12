package com.grupo1.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="tratamiento")
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcionTratamiento;
    private Integer duracionDias; 

    @ManyToOne
    @JoinColumn(name = "diagnostico_id", referencedColumnName = "id")
    @JsonBackReference("DiagnosticoTratamiento")
    private Diagnostico diagnostico;
}
