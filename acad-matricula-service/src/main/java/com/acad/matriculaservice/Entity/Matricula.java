package com.acad.matriculaservice.Entity;


import com.acad.matriculaservice.Dto.RequisitosDto;
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
@Table(name = "matricula")

public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;
        @Column(name = "plan_academico", length = 100)
        private String planAcademico;
        @Column(name = "institucion", length = 100)
        private String institucion;
        @Column(name = "usuario", length = 100)
        private String usuario;
    @Column(name = "codigo_matricula", length = 100)
    private String codigoMatricula;
        @Column(name = "nivel", length = 100)
        private String nivel;

    private Long idRequisitos;
    @Transient
    private RequisitosDto requisitos;


}
