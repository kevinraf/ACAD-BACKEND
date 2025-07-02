package com.acad.requisitoservice.Entity;
import com.acad.requisitoservice.Dto.AntecedenteMedicoDto;
import com.acad.requisitoservice.Dto.ApoderadoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "requisito")
public class Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisitos")
    private Long idRequisitos;

    @Column(name = "dni_estudiante", length = 20)
    private String dniEstudiante;

    @Column(name = "partida_nacimiento_original", length = 100)
    private String partidaNacimientoOriginal;

    @Column(name = "resolucion_rectoral_de_traslado", length = 100)
    private String resolucionRectoralDeTraslado;

    @Column(name = "certificado_de_estudio", length = 100)
    private String certificadoDeEstudio;

    @Column(name = "ficha_matricula_generado_por_siage", length = 100)
    private String fichaMatriculaGeneradoPorSIAGE;

    @Column(name = "constancia_de_comportamiento", length = 100)
    private String constanciaDeComportamiento;

    @Column(name = "constancia_de_no_adeudo", length = 100)
    private String constanciaDeNoAdeudo;

    @Column(name = "familiar_militar", length = 100)
    private String familiarMilitar;


    private Long idApoderado;

    @Transient

    private ApoderadoDto apoderado;


    private Long idAntecedenteMedico;

    @Transient

    private AntecedenteMedicoDto antecedenteMedico;

}
