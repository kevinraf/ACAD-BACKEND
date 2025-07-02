package com.acad.matriculaservice.Service;

import com.acad.matriculaservice.Entity.Matricula;

import java.util.List;

public interface MatriculaServicio {

    List<Matricula> listar();
    Matricula buscar(Long id);
    Matricula guardar(Matricula matricula);
    Matricula actualizar(Matricula matricula);
    void eliminar(Long id);

}
