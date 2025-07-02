package com.acad.antecedentemedicoservice.Service;

import com.acad.antecedentemedicoservice.Entity.AntecedenteMedico;

import java.util.List;
import java.util.Optional;

public interface AntecedenteMedicoServicio {
    List<AntecedenteMedico> listar();
    Optional<AntecedenteMedico> buscar(Long id);
    AntecedenteMedico guardar(AntecedenteMedico antecedente);
    AntecedenteMedico modificar(Long id, AntecedenteMedico antecedente);
    void eliminar(Long id);
}
