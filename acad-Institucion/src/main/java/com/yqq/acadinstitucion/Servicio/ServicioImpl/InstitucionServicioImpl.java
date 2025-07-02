package com.yqq.acadinstitucion.Servicio.ServicioImpl;

import com.yqq.acadinstitucion.Dto.InstitucionDto;
import com.yqq.acadinstitucion.Dto.SedeDto;
import com.yqq.acadinstitucion.Dto.UgelDto;
import com.yqq.acadinstitucion.Entity.Institucion;
import com.yqq.acadinstitucion.Feign.SedeFeign;
import com.yqq.acadinstitucion.Feign.UgelFeign;
import com.yqq.acadinstitucion.Repository.InstitucionRepository;
import com.yqq.acadinstitucion.Servicio.InstitucionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitucionServicioImpl implements InstitucionServicio {

    private final InstitucionRepository repository;
    private final UgelFeign ugelFeign;
    private final SedeFeign sedeFeign;

    @Override
    public InstitucionDto save(Institucion institucion) {
        Institucion saved = repository.save(institucion);
        return mapToResponse(saved);
    }

    @Override
    public List<InstitucionDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public InstitucionDto getById(Integer id) {
        Institucion i = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institución no encontrada con ID: " + id));
        return mapToResponse(i);
    }

    @Override
    public String getNombreSedeById(Long idSede) {
        SedeDto sede = sedeFeign.getSedeById(idSede);
        return (sede != null) ? sede.getNombreSede() : "Sede no encontrada";
    }

    private InstitucionDto mapToResponse(Institucion i) {
        UgelDto ugel = ugelFeign.getUgelById(i.getUgelId());
        SedeDto sede = sedeFeign.getSedeById(i.getSedeId());

        return InstitucionDto.builder()
                .id(i.getIdInstitution())
                .nombre(i.getNombre())
                .direccion(i.getDireccion())
                .ugel(ugel)
                .sede(sede)
                .build();
    }
}
