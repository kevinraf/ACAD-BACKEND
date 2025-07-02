package com.example.ms_kajita.dto;

public class CursoDto {

    private Integer idCurso;
    private String nombre;
    private String descripcion;
    private String horasDeEnsenanzaSemanal;

    @Override
    public String toString() {
        return "CursoDto{" +
                "idCurso=" + idCurso +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", horasDeEnsenanzaSemanal='" + horasDeEnsenanzaSemanal + '\'' +
                '}';
    }

    public CursoDto(Integer idCurso, String nombre, String descripcion, String horasDeEnsenanzaSemanal) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horasDeEnsenanzaSemanal = horasDeEnsenanzaSemanal;
    }

    public CursoDto() {
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
