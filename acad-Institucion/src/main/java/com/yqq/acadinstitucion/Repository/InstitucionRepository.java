package com.yqq.acadinstitucion.Repository;

import com.yqq.acadinstitucion.Entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitucionRepository extends JpaRepository<Institucion, Long> {
    List<Institucion> findByNombreContainingIgnoreCase(String nombre);
}
