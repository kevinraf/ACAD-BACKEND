package com.yqq.acadugel.Servicio;

import com.yqq.acadugel.Entity.Ugel;

import java.util.List;

public interface UgelServicio {
    List<Ugel> findAll();
    Ugel findById(Integer id);
    Ugel save(Ugel ugel);
    void deleteById(Integer id);
}