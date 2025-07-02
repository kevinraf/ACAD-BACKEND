package com.yqq.acadugel.Servicio.ServicioImpl;

import com.yqq.acadugel.Entity.Ugel;
import com.yqq.acadugel.Repocitory.UgelRepository;
import com.yqq.acadugel.Servicio.UgelServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UgelServicioImpl implements UgelServicio {

    @Autowired
    private UgelRepository ugelRepository;

    @Override
    public List<Ugel> findAll() {
        return ugelRepository.findAll();
    }

    @Override
    public Ugel findById(Integer id) {
        return ugelRepository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public Ugel save(Ugel ugel) {
        return ugelRepository.save(ugel);
    }

    @Override
    public void deleteById(Integer id) {
        ugelRepository.deleteById(Long.valueOf(id));
    }
}