package com.academia.acadcargaacademica.Servicio;

import com.academia.acadcargaacademica.Entidad.Planacademico;

import java.util.List;
import java.util.Optional;

public interface PlanacademicoServicio {
    List<Planacademico> listar();
    Optional<Planacademico> buscar(Long id);
    Planacademico guardar(Planacademico planacademico);
    Planacademico modificar(Long id, Planacademico planacademico);
    void eliminar(Long id);

}
