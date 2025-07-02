package com.academia.acadcargaacademica.Repositorio;

import com.academia.acadcargaacademica.Entidad.Planacademico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanacademicoRepo extends JpaRepository <Planacademico, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
