package com.academia.acadcargaacademica.Servicio.Implementos;


import com.academia.acadcargaacademica.Entidad.Planacademico;
import com.academia.acadcargaacademica.Repositorio.PlanacademicoRepo;
import com.academia.acadcargaacademica.Servicio.PlanacademicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanacademicoServicioImplemento implements PlanacademicoServicio {
    @Autowired
    private PlanacademicoRepo repositorio;

    @Override
    public List<Planacademico> listar() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Planacademico> buscar(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Planacademico guardar(Planacademico instancia) {
        return repositorio.save(instancia);
    }

    @Override
    public Planacademico modificar(Long id, Planacademico instancia) {
        instancia.setIdPlanacademico(id);
        return repositorio.save(instancia);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}

