package com.acad.apoderadoservice.Service.ServiceImpl;


import com.acad.apoderadoservice.Entity.Apoderado;
import com.acad.apoderadoservice.Repository.ApoderadoRepositorio;
import com.acad.apoderadoservice.Service.ApoderadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApoderadoServicioImpl implements ApoderadoServicio {

    @Autowired
    private ApoderadoRepositorio apoderadoRepositorio;

    @Override
    public List<Apoderado> listar() {
        return apoderadoRepositorio.findAll();
    }

    @Override
    public Optional<Apoderado> buscar(Long id) {
        return apoderadoRepositorio.findById(id);
    }

    @Override
    public Apoderado guardar(Apoderado apoderado) {
        // Aquí podrías agregar validaciones como se hace con el correo en Usuario
        return apoderadoRepositorio.save(apoderado);
    }

    @Override
    public Apoderado modificar(Long id, Apoderado apoderado) {
        apoderado.setIdApoderado(id);
        return apoderadoRepositorio.save(apoderado);
    }

    @Override
    public void eliminar(Long id) {
        apoderadoRepositorio.deleteById(id);
    }
}