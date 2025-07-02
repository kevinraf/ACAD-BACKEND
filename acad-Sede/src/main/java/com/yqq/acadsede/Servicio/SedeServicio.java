package com.yqq.acadsede.Servicio;

import com.yqq.acadsede.Entity.Sede;

import java.util.List;

public interface SedeServicio {
    List<Sede> findAll();
    Sede findById(Long id);
    Sede save(Sede sede);
    void deleteById(Long id);
}