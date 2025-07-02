package com.yqq.acadinstitucion.Servicio;

import com.yqq.acadinstitucion.Dto.InstitucionDto;
import com.yqq.acadinstitucion.Entity.Institucion;

import java.util.List;

public interface InstitucionServicio {

    InstitucionDto save(Institucion institucion);

    List<InstitucionDto> getAll();

    InstitucionDto getById(Integer id);

    String getNombreSedeById(Long idSede); // ✅ agregado
}
