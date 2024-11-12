package com.grupo1.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "historia_medica")
public class HistoriaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "antecedentes_medicos", joinColumns = @JoinColumn(name = "historia_medica_id"))
    @Column(name = "antecedente")
    private List<String> antecedentesMedicos;

    @ElementCollection
    @CollectionTable(name = "cirugias_anteriores", joinColumns = @JoinColumn(name = "historia_medica_id"))
    @Column(name = "cirugia")
    private List<String> cirugiasAnteriores;

    @ElementCollection
    @CollectionTable(name = "alergias", joinColumns = @JoinColumn(name = "historia_medica_id"))
    @Column(name = "alergia")
    private List<String> alergias;

    @ElementCollection
    @CollectionTable(name = "antecedentes_familiares", joinColumns = @JoinColumn(name = "historia_medica_id"))
    @Column(name = "antecedente")
    private List<String> antecedentesFamiliares;

    @OneToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @JsonBackReference("PacienteHistoria")
    private Paciente paciente;
}
