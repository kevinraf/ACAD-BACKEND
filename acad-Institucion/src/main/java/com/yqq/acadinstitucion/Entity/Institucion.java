package com.yqq.acadinstitucion.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "institucion")
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    @Column(name = "sede_id")
    private Long sedeId;

    @Column(name = "ugel_id")
    private Long ugelId;

    public Institucion() {}

    public Institucion(Long id, String nombre, String direccion, Long sedeId, Long ugelId) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.sedeId = sedeId;
        this.ugelId = ugelId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Long getSedeId() { return sedeId; }
    public void setSedeId(Long sedeId) { this.sedeId = sedeId; }

    public Long getUgelId() { return ugelId; }
    public void setUgelId(Long ugelId) { this.ugelId = ugelId; }
}
