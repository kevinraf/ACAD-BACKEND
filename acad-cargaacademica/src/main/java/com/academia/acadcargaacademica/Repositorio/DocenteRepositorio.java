package com.academia.acadcargaacademica.Repositorio;

import com.academia.acadcargaacademica.Entidad.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocenteRepositorio extends JpaRepository<Docente, Long> {

    // Buscar por DNI
    List<Docente> findByDniDocente(String dniDocente);

    // Buscar por Cargo
    List<Docente> findByCargoDocente(String cargoDocente);

    // Buscar por antigüedad menor a 5 años (recordando que es un String)
    List<Docente> findByAntiguedadDocenteLessThan(String antiguedad);

    // Buscar por antigüedad mayor a 5 años
    List<Docente> findByAntiguedadDocenteGreaterThan(String antiguedad);
}
