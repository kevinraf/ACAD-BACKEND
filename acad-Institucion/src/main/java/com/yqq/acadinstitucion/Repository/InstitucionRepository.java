package com.yqq.acadinstitucion.Repository;


import com.yqq.acadinstitucion.Entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {
}