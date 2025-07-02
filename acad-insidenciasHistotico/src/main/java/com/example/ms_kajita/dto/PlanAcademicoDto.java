package com.example.ms_kajita.dto;

public class PlanAcademicoDto {

    private Integer idPlan;
    private String nombrePlan;
    private Long idSeccion;
    private Long tiempoInicioPlan;
    private Long timepoFinalPlan;

    @Override
    public String toString() {
        return "PlanAcademicoDto{" +
                "idPlan=" + idPlan +
                ", nombrePlan='" + nombrePlan + '\'' +
                ", idSeccion=" + idSeccion +
                ", tiempoInicioPlan=" + tiempoInicioPlan +
                ", timepoFinalPlan=" + timepoFinalPlan +
                '}';
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }


    public PlanAcademicoDto() {
    }

    public PlanAcademicoDto(Integer idPlan, String nombrePlan, Long idSeccion, Long tiempoInicioPlan, Long timepoFinalPlan) {
        this.idPlan = idPlan;
        this.nombrePlan = nombrePlan;
        this.idSeccion = idSeccion;
        this.tiempoInicioPlan = tiempoInicioPlan;
        this.timepoFinalPlan = timepoFinalPlan;
    }




}
