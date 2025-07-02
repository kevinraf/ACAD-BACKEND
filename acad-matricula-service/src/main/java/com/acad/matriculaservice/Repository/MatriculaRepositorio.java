package com.acad.matriculaservice.Repository;

import com.acad.matriculaservice.Entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepositorio extends JpaRepository<Matricula, Long> {
}
