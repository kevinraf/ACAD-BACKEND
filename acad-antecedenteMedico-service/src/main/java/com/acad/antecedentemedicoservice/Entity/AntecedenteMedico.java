package com.acad.antecedentemedicoservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "antecedente_medico")
public class AntecedenteMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAntecedenteMedico")
    private Long idAntecedenteMedico;

    @Column(name = "edad", length = 45)
    private String edad;

    @Column(name = "altura", length = 45)
    private String altura;

    @Column(name = "peso", length = 45)
    private String peso;

    @Column(name = "grupoSanguineo", length = 45)
    private String grupoSanguineo;

    @Column(name = "enfermedadesPadecidas", length = 45)
    private String enfermedadesPadecidas;

    @Column(name = "antecedenteDeHospitalizacionoDeCirugias", length = 45)
    private String antecedenteDeHospitalizacionoDeCirugias;

    @Column(name = "seEncuentraMedicado", length = 45)
    private String seEncuentraMedicado;

    @Column(name = "presentaDiscapacidad", length = 45)
    private String presentaDiscapacidad;

    @Column(name = "alergias", length = 45)
    private String alergias;

    @Column(name = "estadoPsicologico", length = 45)
    private String estadoPsicologico;
}