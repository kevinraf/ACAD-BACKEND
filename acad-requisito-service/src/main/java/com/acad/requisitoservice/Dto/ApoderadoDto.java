package com.acad.requisitoservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ApoderadoDto {
    private Long idApoderado;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento;
    private String direccion;
    private String rolFamilia;
}