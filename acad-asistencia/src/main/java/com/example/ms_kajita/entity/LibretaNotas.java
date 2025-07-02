package com.example.ms_kajita.entity;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class LibretaNotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibretaNotas")
    private Integer idLibretaNotas;
    @Column(name = "Matricula_idMatricula")
    private Integer idMatricula;
    @Transient
    private String codigoMatricula;

    @Column(name = "NotasparaAreaCurricular")
    private Integer idNotasCompetencia;
    @Transient
    private String nota;

    public LibretaNotas(Integer idLibretaNotas, Integer idMatricula, String codigoMatricula, Integer idNotasCompetencia, String nota, String notaFinal, String idPlanAcademico) {
        this.idLibretaNotas = idLibretaNotas;
        this.idMatricula = idMatricula;
        this.codigoMatricula = codigoMatricula;
        this.idNotasCompetencia = idNotasCompetencia;
        this.nota = nota;
        this.notaFinal = notaFinal;
        this.idPlanAcademico = idPlanAcademico;
    }

    @Transient
    private String notaFinal;

    public String getIdPlanAcademico() {
        return idPlanAcademico;
    }

    public void setIdPlanAcademico(String idPlanAcademico) {
        this.idPlanAcademico = idPlanAcademico;
    }

    @Transient
    private String idPlanAcademico;

    public Integer getIdLibretaNotas() {
        return idLibretaNotas;
    }

    public void setIdLibretaNotas(Integer idLibretaNotas) {
        this.idLibretaNotas = idLibretaNotas;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(String codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public Integer getIdNotasCompetencia() {
        return idNotasCompetencia;
    }

    public void setIdNotasCompetencia(Integer idNotasCompetencia) {
        this.idNotasCompetencia = idNotasCompetencia;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(String notaFinal) {
        this.notaFinal = notaFinal;
    }



    @Override
    public String toString() {
        return "Asistencia{" +
                "idLibretaNotas=" + idLibretaNotas +
                ", Matricula_idMatricula=" + idMatricula +
                ", codigoMatricula=" + codigoMatricula +
                ", NotasparaAreaCurricular='" + idNotasCompetencia + '\'' +
                ", nota=" + nota +
                ", notaFinal='" + notaFinal + '\'' +
                '}';
    }

    public LibretaNotas() {

    }


}