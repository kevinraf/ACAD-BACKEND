package com.academia.acadcargaacademica.Servicio;

import com.academia.acadcargaacademica.Entidad.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoServicio {

    List<Curso> listar();
    Optional<Curso> buscar(Long id);
    Curso guardar(Curso curso);
    Curso modificar(Long id, Curso curso);
    void eliminar(Long id);

}
