package com.grupo1.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diagnostico")
public class Diagnostico {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDoctor;
    private String descripcionDiagnostico;
    
    @ManyToOne
    @JoinColumn(name = "consulta_id", referencedColumnName = "id")
    @JsonBackReference("ConsultaDiagnostico")
    private Consulta consulta;

    @OneToMany(mappedBy = "diagnostico", cascade = CascadeType.ALL)
    @JsonManagedReference("DiagnosticoTratamiento")
    private List<Tratamiento> tratamientos = new ArrayList<>();
    
}
