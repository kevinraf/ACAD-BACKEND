package com.acad.apoderadoservice.Service;

import com.acad.apoderadoservice.Entity.Apoderado;

import java.util.List;
import java.util.Optional;

public interface ApoderadoServicio {
    List<Apoderado> listar();
    Optional<Apoderado> buscar(Long id);
    Apoderado guardar(Apoderado apoderado);
    Apoderado modificar(Long id, Apoderado apoderado);
    void eliminar(Long id);
}