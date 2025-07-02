package com.yqq.acadsede.Servicio.ServicioImpl;

import com.yqq.acadsede.Entity.Sede;
import com.yqq.acadsede.Repository.SedeRepository;
import com.yqq.acadsede.Servicio.SedeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SedeServicioImpl implements SedeServicio {

    @Autowired
    private SedeRepository sedeRepository;

    @Override
    public List<Sede> findAll() {
        return sedeRepository.findAll();
    }

    @Override
    public Sede findById(Long id) {
        return sedeRepository.findById(id).orElse(null);
    }

    @Override
    public Sede save(Sede sede) {
        return sedeRepository.save(sede);
    }

    @Override
    public void deleteById(Long id) {
        sedeRepository.deleteById(id);
    }
}