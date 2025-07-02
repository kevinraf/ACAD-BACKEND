package com.example.ms_kajita.dto;

public class DocenteDto {

    private Integer idDocente;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String fechaNacimiento;
    private String profecion;
    private String tiempoEnsenanza;
    private String dni;

    public Integer getidDocente() {
        return idDocente;
    }

    public void setidDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public DocenteDto() {
    }





    @Override
    public String toString() {
        return "DocenteDto{" +
                "idDocente=" + idDocente +
                ", nombre='" + nombre + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", profecion='" + profecion + '\'' +
                ", tiempoEnsenanza='" + tiempoEnsenanza + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }


    public DocenteDto(Integer idDocente, String nombre, String apellidoMaterno, String apellidoPaterno, String fechaNacimiento, String profecion, String tiempoEnsenanza, String dni) {
        this.idDocente = idDocente;
        this.nombre = nombre;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.profecion = profecion;
        this.tiempoEnsenanza = tiempoEnsenanza;
        this.dni = dni;
    }

}
