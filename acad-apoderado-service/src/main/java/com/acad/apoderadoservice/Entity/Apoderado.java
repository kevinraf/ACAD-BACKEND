package com.acad.apoderadoservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "apoderado")
public class Apoderado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idApoderado")
    private Long idApoderado;

    @Column(name = "nombres", length = 45)
    private String nombres;

    @Column(name = "apellido_paterno", length = 45)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 45)
    private String apellidoMaterno;

    @Column(name = "fechaNacimiento", length = 45)
    private String fechaNacimiento;

    @Column(name = "direccion", length = 45)
    private String direccion;

    @Column(name = "RolFamilia", length = 45)
    private String rolFamilia;
}