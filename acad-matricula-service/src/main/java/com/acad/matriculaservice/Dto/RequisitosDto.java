package com.acad.matriculaservice.Dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RequisitosDto {
    private Long idRequisitos;
    private String dniEstudiante;
    private String partidaNacimientoOriginal;
    private String resolucionRectoralDeTraslado;
    private String certificadoDeEstudio;
    private String fichaMatriculaGeneradoPorSIAGE;
    private String constanciaDeComportamiento;
    private String constanciaDeNoAdeudo;
    private String familiarMilitar;
    private Long idApoderado;
    private Long idAntecedenteMedico;

    //aqui agregamos mas los que queremos que se muestren en nuestra consulta para completar todos los campos

    //agregamos despues para los que si queremos que esten como los de apoderado y antecedentemedico
}
