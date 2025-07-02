package com.yqq.acadinstitucion.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstitucionDto {
    private Integer id;
    private String nombre;
    private String direccion;

    private UgelDto ugel;
    private SedeDto sede;
}