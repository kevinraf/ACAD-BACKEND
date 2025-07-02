package com.acad.requisitoservice.Dto;

import lombok.Data;

@Data
public class AntecedenteMedicoDto {
    private Long idAntecedenteMedico;
    private String edad;
    private String altura;
    private String peso;
    private String grupoSanguineo;
    private String enfermedadesPadecidas;
    private String antecedenteDeHospitalizacionoDeCirugias;
    private String seEncuentraMedicado;
    private String presentaDiscapacidad;
    private String alergias;
    private String estadoPsicologico;
}
