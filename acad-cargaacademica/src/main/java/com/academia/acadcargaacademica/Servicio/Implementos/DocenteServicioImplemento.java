package com.academia.acadcargaacademica.Servicio.Implementos;


import com.academia.acadcargaacademica.Entidad.Curso;
import com.academia.acadcargaacademica.Entidad.Docente;
import com.academia.acadcargaacademica.Repositorio.CursoRepositorio;
import com.academia.acadcargaacademica.Repositorio.DocenteRepositorio;
import com.academia.acadcargaacademica.Servicio.DocenteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServicioImplemento implements DocenteServicio {
    @Autowired
    private DocenteRepositorio repositorio;

    @Override
    public List<Docente> listar() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Docente> buscar(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Docente guardar(Docente instancia) {
        return repositorio.save(instancia);
    }

    @Override
    public Docente modificar(Long id, Docente instancia) {
        instancia.setIdDocente(id);
        return repositorio.save(instancia);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    // --- las demas funcionalidades  ---

    @Override
    public List<Docente> buscarPorDni(String dni) {
        return repositorio.findByDniDocenteContainingIgnoreCase(dni); // sin condición de longitud
    }

    @Override
    public List<Docente> buscarPorCargo(String cargo) {
        return repositorio.findByCargoDocenteContainingIgnoreCase(cargo);
    }


    @Override
    public List<Docente> buscarPorAntiguedadMenorA5() {
        return repositorio.findByAntiguedadDocenteLessThanEqual(5);
    }

    @Override
    public List<Docente> buscarPorAntiguedadMayorA5() {
        return repositorio.findByAntiguedadDocenteGreaterThanEqual(5);
    }



}

