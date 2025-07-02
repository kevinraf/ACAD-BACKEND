package com.example.ms_kajita.dto;

public class NotasparaAreaCurricularDto {

    public NotasparaAreaCurricularDto() {
    }

    public NotasparaAreaCurricularDto(Integer idNotasCompetencia, String nota, String notaFinal, String idPlanAcademico) {
        this.idNotasCompetencia = idNotasCompetencia;
        this.nota = nota;
        this.notaFinal = notaFinal;
        this.idPlanAcademico = idPlanAcademico;
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

    public String getIdPlanAcademico() {
        return idPlanAcademico;
    }

    public void setIdPlanAcademico(String idPlanAcademico) {
        this.idPlanAcademico = idPlanAcademico;
    }


    @Override
    public String toString() {
        return "NotasparaAreaCurricularDto{" +
                "idNotasCompetencia=" + idNotasCompetencia +
                ", nota=" + nota +
                ", notaFinal='" + notaFinal + '\'' +
                ", idPlanAcademico='" + idPlanAcademico + '\'' +
                '}';
    }

    private Integer idNotasCompetencia;
    private String nota;
    private String notaFinal;
    private String idPlanAcademico;

}
