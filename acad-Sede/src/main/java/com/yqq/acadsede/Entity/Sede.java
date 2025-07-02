package com.yqq.acadsede.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sede")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSede;

    @Column(name = "nombre_sede", length = 100, nullable = false)
    private String nombreSede;

    // Constructor sin argumentos (necesario para JPA)
    public Sede() {
    }

    // Constructor con argumentos (para seeder)
    public Sede(Long idSede, String nombreSede) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
    }

    // Getters y Setters
    public Long getIdSede() {
        return idSede;
    }

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }
}
