package com.example.ms_kajita.service.impl;


import com.example.ms_kajita.dto.*;

import com.example.ms_kajita.dto.CursoDto;
import com.example.ms_kajita.dto.DocenteDto;
import com.example.ms_kajita.dto.PlanAcademicoDto;
import com.example.ms_kajita.dto.AuthUser;
import com.example.ms_kajita.entity.Asistencia;
import com.example.ms_kajita.feing.CursoFeing;
import com.example.ms_kajita.feing.DocenteFeing;
import com.example.ms_kajita.feing.PlanAcademicoFeing;
import com.example.ms_kajita.feing.UsuarioFeign;
import com.example.ms_kajita.repository.AsistenciaRepository;
import com.example.ms_kajita.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;
    @Autowired
    private UsuarioFeign usuarioFeign; // Correcto: inyectaste catalogoFeign
    @Autowired
    private CursoFeing cursoFeing;
    @Autowired
    private DocenteFeing docenteFeing;
    @Autowired
    private PlanAcademicoFeing planAcademicoFeing;

    @Override
    public List<Asistencia> listar() {
        return asistenciaRepository.findAll().stream()
                .map(asistencia -> {
                    // Usuario
                    if (asistencia.getUsuarioIdUsuario() != null) {
                        String usuarioNombre = obtenerUsuarioNombre(asistencia.getUsuarioIdUsuario().intValue());
                        asistencia.setUsuarioNombre(usuarioNombre);
                    }
                    // Curso
                    if (asistencia.getCursoIdCurso() != null) {
                        String cursoNombre = obtenerCursoNombre(asistencia.getCursoIdCurso());
                        asistencia.setCursoNombre(cursoNombre);
                    }
                    // Docente
                    if (asistencia.getDocenteIdDocente() != null) {
                        String docenteNombre = obtenerDocenteNombre(asistencia.getDocenteIdDocente());
                        asistencia.setDocenteNombre(docenteNombre);
                    }
                    // Plan académico
                    if (asistencia.getPlanAcademicoIdPlanAcademico() != null) {
                        String planNombre = obtenerPlanAcademicoNombre(asistencia.getPlanAcademicoIdPlanAcademico());
                        asistencia.setPlanAcademicoNombre(planNombre);
                    }
                    return asistencia;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Asistencia> listarPorId(Integer id) {
        Optional<Asistencia> asistenciaOptional = asistenciaRepository.findById(id);
        if (asistenciaOptional.isPresent()) {
            Asistencia asistencia = asistenciaOptional.get();
            asistencia.setUsuarioNombre(obtenerUsuarioNombre(asistencia.getUsuarioIdUsuario().intValue()));
            asistencia.setCursoNombre(obtenerCursoNombre(asistencia.getCursoIdCurso()));
            asistencia.setDocenteNombre(obtenerDocenteNombre(asistencia.getDocenteIdDocente()));
            asistencia.setPlanAcademicoNombre(obtenerPlanAcademicoNombre(asistencia.getPlanAcademicoIdPlanAcademico()));
            return Optional.of(asistencia);
        }
        return Optional.empty();
    }

    @Override
    public Asistencia guardar(Asistencia asistencia) {
        Asistencia asistenciaGuardada = asistenciaRepository.save(asistencia);
        asistenciaGuardada.setUsuarioNombre(obtenerUsuarioNombre(asistenciaGuardada.getUsuarioIdUsuario().intValue()));
        asistenciaGuardada.setCursoNombre(obtenerCursoNombre(asistenciaGuardada.getCursoIdCurso()));
        asistenciaGuardada.setDocenteNombre(obtenerDocenteNombre(asistenciaGuardada.getDocenteIdDocente()));
        asistenciaGuardada.setPlanAcademicoNombre(obtenerPlanAcademicoNombre(asistenciaGuardada.getPlanAcademicoIdPlanAcademico()));
        return asistenciaGuardada;
    }

    @Override
    public Asistencia actualizar(Asistencia asistencia) {
        Asistencia asistenciaActualizada = asistenciaRepository.save(asistencia);
        asistenciaActualizada.setUsuarioNombre(obtenerUsuarioNombre(asistenciaActualizada.getUsuarioIdUsuario().intValue()));
        asistenciaActualizada.setCursoNombre(obtenerCursoNombre(asistenciaActualizada.getCursoIdCurso()));
        asistenciaActualizada.setDocenteNombre(obtenerDocenteNombre(asistenciaActualizada.getDocenteIdDocente()));
        asistenciaActualizada.setPlanAcademicoNombre(obtenerPlanAcademicoNombre(asistenciaActualizada.getPlanAcademicoIdPlanAcademico()));
        return asistenciaActualizada;
    }



    @Override
    public void eliminarPorId(Integer id) {
        asistenciaRepository.deleteById(id);
    }


    private String obtenerUsuarioNombre(Integer idUsuario) {
        try {
            AuthUser authUser = usuarioFeign.buscarAuthUser(idUsuario).getBody();
            if (authUser != null) {
                return authUser.getUser();
            }
        } catch (Exception e) {
            return "Usuario no encontrado";
        }
        return null;
    }

    private String obtenerCursoNombre(Integer idCurso) {
        try {
            CursoDto cursoDto = cursoFeing.buscarCurso(idCurso).getBody();
            if (cursoDto != null) {
                return cursoDto.getNombre();
            }
        } catch (Exception e) {
            return "Curso no encontrado";
        }
        return null;
    }

    private String obtenerDocenteNombre(Integer idDocente) {
        try {
            DocenteDto docenteDto = docenteFeing.buscarDocente(idDocente).getBody();
            if (docenteDto != null) {
                return docenteDto.getNombre();
            }
        } catch (Exception e) {
            return "Docente no encontrado";
        }
        return null;
    }

    private String obtenerPlanAcademicoNombre(Integer idPlan) {
        try {
            PlanAcademicoDto planAcademicoDto = planAcademicoFeing.buscarPlanAcademico(idPlan).getBody();
            if (planAcademicoDto != null) {
                return planAcademicoDto.getNombrePlan();
            }
        } catch (Exception e) {
            return "Plan académico no encontrado";
        }
        return null;
    }

    // Helper para enriquecer nombres
    private Asistencia enriquecer(Asistencia asistencia) {
        asistencia.setUsuarioNombre(obtenerUsuarioNombre(asistencia.getUsuarioIdUsuario().intValue()));
        asistencia.setCursoNombre(obtenerCursoNombre(asistencia.getCursoIdCurso()));
        asistencia.setDocenteNombre(obtenerDocenteNombre(asistencia.getDocenteIdDocente()));
        asistencia.setPlanAcademicoNombre(obtenerPlanAcademicoNombre(asistencia.getPlanAcademicoIdPlanAcademico()));
        return asistencia;
    }

    @Override
    public List<Asistencia> listarPorNombreUsuario(String nombreUsuario) {
        // filtrado en memoria sobre el listado enriquecido
        return listar().stream()
                .filter(a -> a.getUsuarioNombre()
                        .toLowerCase()
                        .contains(nombreUsuario.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Asistencia> listarPorFechaPorDia(LocalDate desde, LocalDate hasta) {
        // convertir al inicio y fin de cada día
        LocalDateTime inicio = desde.atStartOfDay();
        LocalDateTime fin    = hasta.atTime(LocalTime.MAX);

        return asistenciaRepository
                .findByFechaRegistroAsistenciaBetween(inicio, fin)
                .stream()
                .map(this::enriquecer)
                .collect(Collectors.toList());
    }

    @Override
    public List<Asistencia> listarPorNombreCurso(String nombreCurso) {
        return listar().stream()
                .filter(a -> a.getCursoNombre()
                        .toLowerCase()
                        .contains(nombreCurso.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<UsuarioEstadoStatsDto> agruparPorEstado(String estadoBuscado) {
        // 1. Recupera todas las asistencias enriquecidas
        List<Asistencia> todas = listar();

        // 2. Filtra por el estado (ignore case) y agrupa
        Map<Long, UsuarioEstadoStatsDto> mapa = new HashMap<>();
        todas.stream()
                .filter(a -> a.getEstadoAsistencia() != null
                        && a.getEstadoAsistencia().equalsIgnoreCase(estadoBuscado))
                .forEach(a -> {
                    Long uid = a.getUsuarioIdUsuario();
                    mapa.compute(uid, (id, dto) -> {
                        if (dto == null) {
                            // primera vez que vemos a este usuario
                            return new UsuarioEstadoStatsDto(
                                    id,
                                    a.getUsuarioNombre(),
                                    1
                            );
                        } else {
                            dto.setCantidad(dto.getCantidad() + 1);
                            return dto;
                        }
                    });
                });

        // 3. Ordenar descendentemente por cantidad y devolver lista
        return mapa.values().stream()
                .sorted(Comparator.comparingLong(UsuarioEstadoStatsDto::getCantidad).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioEstadoStatsDto> usuariosConMasFaltas() {
        return agruparPorEstado("falta");
    }

    @Override
    public List<UsuarioEstadoStatsDto> usuariosConMasTardanzas() {
        return agruparPorEstado("tardanza");
    }

    @Override
    public List<UsuarioEstadoStatsDto> usuariosConMasPresentes() {
        return agruparPorEstado("presente");
    }
}
