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

    @Override
    public String toString() {
        return "Asistencia{" +
                "idAsistencia=" + idAsistencia +
                ", fechaRegistroAsistencia=" + fechaRegistroAsistencia +
                ", usuarioIdUsuario=" + usuarioIdUsuario +
                ", usuarioNombre='" + usuarioNombre + '\'' +
                ", cursoIdCurso=" + cursoIdCurso +
                ", cursoNombre='" + cursoNombre + '\'' +
                ", docenteIdDocente=" + docenteIdDocente +
                ", docenteNombre='" + docenteNombre + '\'' +
                ", estadoAsistencia='" + estadoAsistencia + '\'' +
                ", planAcademicoIdPlanAcademico=" + planAcademicoIdPlanAcademico +
                ", planAcademicoNombre='" + planAcademicoNombre + '\'' +
                '}';
    }

    public Asistencia() {
    }

    public Asistencia(Integer idAsistencia, LocalDateTime fechaRegistroAsistencia, Long usuarioIdUsuario, String usuarioNombre, Integer cursoIdCurso, String cursoNombre, Integer docenteIdDocente, String docenteNombre, String estadoAsistencia, Integer planAcademicoIdPlanAcademico, String planAcademicoNombre) {
        this.idAsistencia = idAsistencia;
        this.fechaRegistroAsistencia = fechaRegistroAsistencia;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.usuarioNombre = usuarioNombre;
        this.cursoIdCurso = cursoIdCurso;
        this.cursoNombre = cursoNombre;
        this.docenteIdDocente = docenteIdDocente;
        this.docenteNombre = docenteNombre;
        this.estadoAsistencia = estadoAsistencia;
        this.planAcademicoIdPlanAcademico = planAcademicoIdPlanAcademico;
        this.planAcademicoNombre = planAcademicoNombre;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public LocalDateTime getFechaRegistroAsistencia() {
        return fechaRegistroAsistencia;
    }

    public void setFechaRegistroAsistencia(LocalDateTime fechaRegistroAsistencia) {
        this.fechaRegistroAsistencia = fechaRegistroAsistencia;
    }

    public Long getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Long usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public Integer getCursoIdCurso() {
        return cursoIdCurso;
    }

    public void setCursoIdCurso(Integer cursoIdCurso) {
        this.cursoIdCurso = cursoIdCurso;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public Integer getDocenteIdDocente() {
        return docenteIdDocente;
    }

    public void setDocenteIdDocente(Integer docenteIdDocente) {
        this.docenteIdDocente = docenteIdDocente;
    }

    public String getDocenteNombre() {
        return docenteNombre;
    }

    public void setDocenteNombre(String docenteNombre) {
        this.docenteNombre = docenteNombre;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public Integer getPlanAcademicoIdPlanAcademico() {
        return planAcademicoIdPlanAcademico;
    }

    public void setPlanAcademicoIdPlanAcademico(Integer planAcademicoIdPlanAcademico) {
        this.planAcademicoIdPlanAcademico = planAcademicoIdPlanAcademico;
    }

    public String getPlanAcademicoNombre() {
        return planAcademicoNombre;
    }

    public void setPlanAcademicoNombre(String planAcademicoNombre) {
        this.planAcademicoNombre = planAcademicoNombre;
    }

}