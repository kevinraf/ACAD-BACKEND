package com.acad.matriculaservice.Service.serviceImpl;

import com.acad.matriculaservice.Dto.RequisitosDto;
import com.acad.matriculaservice.Entity.Matricula;
import com.acad.matriculaservice.Feign.RequisitosFeign;
import com.acad.matriculaservice.Repository.MatriculaRepositorio;
import com.acad.matriculaservice.Service.MatriculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaServicioImpl implements MatriculaServicio {

    @Autowired
    private MatriculaRepositorio matriculaRepositorio;

    @Autowired
    private RequisitosFeign requisitosFeign;

    @Override
    public List<Matricula> listar() {
        return matriculaRepositorio.findAll().stream()
                .map(matricula -> {
                    if (matricula.getIdRequisitos() != null) {
                        RequisitosDto requisitos = requisitosFeign.buscarRequisitos(matricula.getIdRequisitos()).getBody();
                        matricula.setRequisitos(requisitos);
                    }
                    return matricula;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Matricula buscar(Long id) {
        Optional<Matricula> matriculaOptional = matriculaRepositorio.findById(id);
        if (matriculaOptional.isPresent()) {
            Matricula matricula = matriculaOptional.get();
            if (matricula.getIdRequisitos() != null) {
                RequisitosDto requisitos = requisitosFeign.buscarRequisitos(matricula.getIdRequisitos()).getBody();
                matricula.setRequisitos(requisitos);
            }
            return matricula;
        }
        return null;
    }

    @Override
    public Matricula guardar(Matricula matricula) {
        // Si hay un objeto requisitos (DTO), se debe crear vía Feign
        if (matricula.getRequisitos() != null) {
            ResponseEntity<RequisitosDto> resp = requisitosFeign.crearRequisitos(matricula.getRequisitos());
            if (resp.getStatusCode().is2xxSuccessful()) {
                RequisitosDto requisitos = resp.getBody();
                matricula.setIdRequisitos(requisitos.getIdRequisitos()); // Actualiza ID antes de guardar
            } else {
                throw new IllegalStateException("No se pudo crear el requisito.");
            }
        }

        Matricula matriculaGuardada = matriculaRepositorio.save(matricula);

        // Poblar el DTO si se tiene el ID
        if (matriculaGuardada.getIdRequisitos() != null) {
            RequisitosDto requisitos = requisitosFeign.buscarRequisitos(matriculaGuardada.getIdRequisitos()).getBody();
            matriculaGuardada.setRequisitos(requisitos);
        }

        return matriculaGuardada;
    }

    @Override
    public Matricula actualizar(Matricula matricula) {
        // Aquí se asume que los requisitos ya existen, solo se actualiza el ID
        Matricula actualizada = matriculaRepositorio.save(matricula);
        if (actualizada.getIdRequisitos() != null) {
            RequisitosDto requisitos = requisitosFeign.buscarRequisitos(actualizada.getIdRequisitos()).getBody();
            actualizada.setRequisitos(requisitos);
        }
        return actualizada;
    }

    @Override
    public void eliminar(Long id) {
        matriculaRepositorio.deleteById(id);
    }
}
