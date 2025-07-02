package com.example.ms_kajita.dto;

public class MatriculaDto {

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

    private Integer idMatricula;
    private String codigoMatricula;

    public MatriculaDto() {
    }

    public MatriculaDto(Integer idMatricula, String codigoMatricula) {
        this.idMatricula = idMatricula;
        this.codigoMatricula = codigoMatricula;
    }

    @Override
    public String toString() {
        return "CursoDto{" +
                "idMatricula=" + idMatricula +
                ", codigoMatricula='" + codigoMatricula + '\'' +
                '}';
    }

}
