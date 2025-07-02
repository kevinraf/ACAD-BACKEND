package com.academia.acadcargaacademica.Servicio.Implementos;

import com.academia.acadcargaacademica.Entidad.Curso;
import com.academia.acadcargaacademica.Repositorio.CursoRepositorio;
import com.academia.acadcargaacademica.Servicio.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServicioImplemento implements CursoServicio {
    @Autowired
    private CursoRepositorio repositorio;

    @Override
    public List<Curso> listar() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Curso> buscar(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Curso guardar(Curso instancia) {
        return repositorio.save(instancia);
    }

    @Override
    public Curso modificar(Long id, Curso instancia) {
        instancia.setIdCurso(id);
        return repositorio.save(instancia);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
