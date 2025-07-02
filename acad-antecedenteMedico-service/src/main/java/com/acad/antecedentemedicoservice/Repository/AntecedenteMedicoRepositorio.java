package com.acad.antecedentemedicoservice.Repository;

import com.acad.antecedentemedicoservice.Entity.AntecedenteMedico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedenteMedicoRepositorio extends JpaRepository<AntecedenteMedico, Long> {
    // Puedes agregar métodos personalizados aquí si lo necesitas
}