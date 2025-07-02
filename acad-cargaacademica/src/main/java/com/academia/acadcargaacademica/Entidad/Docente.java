package com.academia.acadcargaacademica.Entidad;

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
@Table(name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDocente")
    private Long idDocente;

    private String nombreDocente;
    private String apellidoPaternoDocente;
    private String apellidoMaternoDocente;
    private String correoDocente;
    private String telefonoDocente;
    private String estadoDocente;
    private String cargoDocente;
    private String dniDocente;
    private String antiguedadDocente;

}
