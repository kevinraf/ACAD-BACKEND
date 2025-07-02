package com.acad.requisitoservice.Service;

import com.acad.requisitoservice.Entity.Requisito;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface RequisitoServicio {

    List<Requisito> listar();
    Requisito buscar(Long id);
    Requisito guardar(Requisito requisito);
    Requisito actualizar(Requisito requisito);
    void eliminar(Long id);

}