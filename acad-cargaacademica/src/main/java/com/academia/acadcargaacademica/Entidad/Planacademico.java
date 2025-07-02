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
@Table(name = "planacademico")
public class Planacademico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlanacademico")
    private Long idPlanacademico;

    private String nombrePlanacademico;
    private String descripcionPlanacademico;
    private String estadoPlanacademico;

}
