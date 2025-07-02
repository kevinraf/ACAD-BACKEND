package com.academia.acadcargaacademica.Repositorio;

import com.academia.acadcargaacademica.Entidad.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocenteRepositorio extends JpaRepository<Docente, Long> {

    // Buscar por coincidencia parcial en DNI (mínimo 4 letras)
    List<Docente> findByDniDocenteContainingIgnoreCase(String parteDni);

    // Buscar por coincidencia parcial en cargo
    List<Docente> findByCargoDocenteContainingIgnoreCase(String parteCargo);

    // Antigüedad numérica
    List<Docente> findByAntiguedadDocenteLessThanEqual(Integer valor);    // incluye el 5
    List<Docente> findByAntiguedadDocenteGreaterThanEqual(Integer valor); // incluye el 5

}
