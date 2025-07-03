package com.yqq.acadinstitucion.Servicio;

import com.yqq.acadinstitucion.Dto.InstitucionResponse;
import com.yqq.acadinstitucion.Entity.Institucion;

import java.util.List;

public interface InstitucionService {

    // Crear nueva institución
    Institucion save(Institucion institucion);

    // Obtener todas las instituciones
    List<Institucion> listar();

    // Buscar institución por ID
    Institucion buscar(Long id);

    // Actualizar institución existente
    Institucion actualizar(Institucion institucion);

    // Eliminar institución por ID
    void eliminar(Long id);

    // Buscar instituciones por nombre (usa DTO con sede y ugel)
    List<InstitucionResponse> findByNombre(String nombre);

    // Listar todas las instituciones con nombre de sede y ugel (DTO)
    List<InstitucionResponse> findAllConDatosCompletos();
}
