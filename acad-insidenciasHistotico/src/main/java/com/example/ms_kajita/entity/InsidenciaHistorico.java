package com.example.ms_kajita.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
public class InsidenciaHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInsidenciasHistorico")
    private Integer idInsidenciasHistorico;

    @Column(name = "fechaRegistro")
    private LocalDateTime fechaRegistro; // Corrected typo in field name for consistency

    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripccion")
    private String descripccion;// Changed to Long to match BIGINT in SQL

    @Column(name = "usuario_id_usuario")
    private Long usuarioIdUsuario; // Changed to Long to match BIGINT in SQL
    @Transient
    private String usuarioNombre;

    @Column(name = "Docente_idDocente")
    private Integer docenteIdDocente;
    @Transient
    private String docenteNombre;

    @Column(name = "PlanAcademico_idPlanAcademico")
    private Integer planAcademicoIdPlanAcademico;
    @Transient
    private String planAcademicoNombre;


    public Integer getIdInsidenciasHistorico() {
        return idInsidenciasHistorico;
    }

    public void setIdInsidenciasHistorico(Integer idInsidenciasHistorico) {
        this.idInsidenciasHistorico = idInsidenciasHistorico;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
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

    public InsidenciaHistorico(Integer idInsidenciasHistorico, LocalDateTime fechaRegistro, String titulo, String descripccion, Long usuarioIdUsuario, String usuarioNombre, Integer docenteIdDocente, String docenteNombre, Integer planAcademicoIdPlanAcademico, String planAcademicoNombre) {
        this.idInsidenciasHistorico = idInsidenciasHistorico;
        this.fechaRegistro = fechaRegistro;
        this.titulo = titulo;
        this.descripccion = descripccion;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.usuarioNombre = usuarioNombre;
        this.docenteIdDocente = docenteIdDocente;
        this.docenteNombre = docenteNombre;
        this.planAcademicoIdPlanAcademico = planAcademicoIdPlanAcademico;
        this.planAcademicoNombre = planAcademicoNombre;
    }



    @Override
    public String toString() {
        return "Asistencia{" +
                "idInsidenciasHistorico=" + idInsidenciasHistorico +
                ", fechaRegistro=" + fechaRegistro +
                ", titulo='" + titulo + '\'' +
                ", descripccion='" + descripccion + '\'' +
                ", usuarioIdUsuario=" + usuarioIdUsuario +
                ", usuarioNombre='" + usuarioNombre + '\'' +
                ", docenteIdDocente=" + docenteIdDocente +
                ", docenteNombre='" + docenteNombre + '\'' +
                ", planAcademicoIdPlanAcademico=" + planAcademicoIdPlanAcademico +
                ", planAcademicoNombre='" + planAcademicoNombre + '\'' +
                '}';
    }

    public InsidenciaHistorico() {
    }


}