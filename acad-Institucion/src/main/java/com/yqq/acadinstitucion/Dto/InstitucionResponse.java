package com.yqq.acadinstitucion.Dto;

public class InstitucionResponse {
    private Long id;
    private String nombre;
    private String direccion;
    private String nombreSede;
    private String nombreUgel;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getNombreUgel() {
        return nombreUgel;
    }

    public void setNombreUgel(String nombreUgel) {
        this.nombreUgel = nombreUgel;
    }
}