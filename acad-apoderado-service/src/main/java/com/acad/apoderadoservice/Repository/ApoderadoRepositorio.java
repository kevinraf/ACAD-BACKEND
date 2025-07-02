package com.acad.apoderadoservice.Repository;

import com.acad.apoderadoservice.Entity.Apoderado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApoderadoRepositorio extends JpaRepository<Apoderado, Long> {
    boolean existsByDireccion(String direccion);
}