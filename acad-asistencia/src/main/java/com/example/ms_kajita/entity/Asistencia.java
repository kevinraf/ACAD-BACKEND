package com.example.ms_kajita.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAsistencia")
    private Integer idAsistencia;

    @Column(name = "fechaReghistroAsistencia")
    private LocalDateTime fechaRegistroAsistencia; // Corrected typo in field name for consistency

    @Column(name = "usuario_id_usuario")
    private Long usuarioIdUsuario; // Changed to Long to match BIGINT in SQL
    @Transient
    private String usuarioNombre;

    @Column(name = "Curso_idCurso")
    private Integer cursoIdCurso;
    @Transient
    private String cursoNombre;

    @Column(name = "Docente_idDocente")
    private Integer docenteIdDocente;
    @Transient
    private String docenteNombre;

    @Column(name = "ESTADOASISTENCIA")
    private String estadoAsistencia;

    @Column(name = "PlanAcademico_idPlanAcademico")
    private Integer planAcademicoIdPlanAcademico;
    @Transient
    private String planAcademicoNombre;

}