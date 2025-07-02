package com.yqq.acadugel.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ugel")
public class Ugel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUgel;

    @Column(name = "nombre_ugel", length = 100, nullable = false)
    private String nombreDeLaUgel;

    // Constructor vacío (obligatorio para JPA)
    public Ugel() {}

    // Constructor con parámetros (para el seeder)
    public Ugel(Long idUgel, String nombreDeLaUgel) {
        this.idUgel = idUgel;
        this.nombreDeLaUgel = nombreDeLaUgel;
    }

    // Getters y Setters
    public Long getIdUgel() {
        return idUgel;
    }

    public void setIdUgel(long idUgel) {
        this.idUgel = idUgel;
    }

    public String getNombreDeLaUgel() {
        return nombreDeLaUgel;
    }

    public void setNombreDeLaUgel(String nombreDeLaUgel) {
        this.nombreDeLaUgel = nombreDeLaUgel;
    }
}
