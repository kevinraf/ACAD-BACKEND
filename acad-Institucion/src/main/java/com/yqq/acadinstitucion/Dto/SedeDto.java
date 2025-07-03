
package com.yqq.acadinstitucion.Dto;

public class SedeDto {
    private Long idSede;
    private String nombreSede;

    // Getter
    public String getNombreSede() {
        return nombreSede;
    }

    // Setter
    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    // También para idSede
    public Long getIdSede() {
        return idSede;
    }

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }
}

