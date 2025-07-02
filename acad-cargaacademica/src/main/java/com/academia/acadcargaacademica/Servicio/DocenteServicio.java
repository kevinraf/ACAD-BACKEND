package com.academia.acadcargaacademica.Servicio;

import com.academia.acadcargaacademica.Entidad.Docente;

import java.util.List;
import java.util.Optional;

public interface DocenteServicio {
    List<Docente> listar();
    Optional<Docente> buscar(Long id);
    Docente guardar(Docente docente);
    Docente modificar(Long id, Docente docente);
    void eliminar(Long id);


    List<Docente> buscarPorDni(String dni);
    List<Docente> buscarPorCargo(String cargo);
    List<Docente> buscarPorAntiguedadMenorA5();
    List<Docente> buscarPorAntiguedadMayorA5();

}
