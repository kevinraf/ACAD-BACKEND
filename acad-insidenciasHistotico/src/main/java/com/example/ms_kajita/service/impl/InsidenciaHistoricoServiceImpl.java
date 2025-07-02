package com.example.ms_kajita.service.impl;

import com.example.ms_kajita.dto.DocenteDto;
import com.example.ms_kajita.dto.PlanAcademicoDto;
import com.example.ms_kajita.dto.AuthUser;
import com.example.ms_kajita.entity.InsidenciaHistorico;
import com.example.ms_kajita.feing.DocenteFeing;
import com.example.ms_kajita.feing.PlanAcademicoFeing;
import com.example.ms_kajita.feing.UsuarioFeign;
import com.example.ms_kajita.repository.InsidenciaHistoricoRepository;
import com.example.ms_kajita.service.InsidenciaHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InsidenciaHistoricoServiceImpl implements InsidenciaHistoricoService {

    @Autowired
    private InsidenciaHistoricoRepository insidenciaHistoricoRepository;
    @Autowired
    private UsuarioFeign usuarioFeign; // Correcto: inyectaste catalogoFeign
    @Autowired
    private DocenteFeing docenteFeing;
    @Autowired
    private PlanAcademicoFeing planAcademicoFeing;

    @Override
    public List<InsidenciaHistorico> listar() {
        return insidenciaHistoricoRepository.findAll().stream()
                .map(insidenciaHistorico -> {
                    // Validar nulls si es necesario para evitar errores en tiempo de ejecución
                    if (insidenciaHistorico.getUsuarioIdUsuario() != null) {
                        insidenciaHistorico.setUsuarioNombre(obtenerUsuarioNombre(insidenciaHistorico.getUsuarioIdUsuario().intValue()));
                    }
                    if (insidenciaHistorico.getDocenteIdDocente() != null) {
                        insidenciaHistorico.setDocenteNombre(obtenerDocenteNombre(insidenciaHistorico.getDocenteIdDocente()));
                    }
                    if (insidenciaHistorico.getPlanAcademicoIdPlanAcademico() != null) {
                        insidenciaHistorico.setPlanAcademicoNombre(obtenerPlanAcademicoNombre(insidenciaHistorico.getPlanAcademicoIdPlanAcademico()));
                    }
                    return insidenciaHistorico;
                })
                .collect(Collectors.toList());
    }

    @Override
    public InsidenciaHistorico guardar(InsidenciaHistorico insidenciaHistorico) {
        InsidenciaHistorico insidenciaHistoricoGuardada = insidenciaHistoricoRepository.save(insidenciaHistorico);
        insidenciaHistoricoGuardada.setUsuarioNombre(obtenerUsuarioNombre(insidenciaHistoricoGuardada.getUsuarioIdUsuario().intValue()));
        insidenciaHistoricoGuardada.setDocenteNombre(obtenerDocenteNombre(insidenciaHistoricoGuardada.getDocenteIdDocente()));
        insidenciaHistoricoGuardada.setPlanAcademicoNombre(obtenerPlanAcademicoNombre(insidenciaHistoricoGuardada.getPlanAcademicoIdPlanAcademico()));
        return insidenciaHistoricoGuardada;
    }

    @Override
    public InsidenciaHistorico actualizar(InsidenciaHistorico insidenciaHistorico) {
        InsidenciaHistorico insidenciaHistoricoActualizada = insidenciaHistoricoRepository.save(insidenciaHistorico);
        insidenciaHistoricoActualizada.setUsuarioNombre(obtenerUsuarioNombre(insidenciaHistoricoActualizada.getUsuarioIdUsuario().intValue()));
        insidenciaHistoricoActualizada.setDocenteNombre(obtenerDocenteNombre(insidenciaHistoricoActualizada.getDocenteIdDocente()));
        insidenciaHistoricoActualizada.setPlanAcademicoNombre(obtenerPlanAcademicoNombre(insidenciaHistoricoActualizada.getPlanAcademicoIdPlanAcademico()));
        return insidenciaHistoricoActualizada;
    }

    @Override
    public Optional<InsidenciaHistorico> listarPorId(Integer id) {
        Optional<InsidenciaHistorico> asistenciaOptional = insidenciaHistoricoRepository.findById(id);
        if (asistenciaOptional.isPresent()) {
            InsidenciaHistorico insidenciaHistorico = asistenciaOptional.get();
            insidenciaHistorico.setUsuarioNombre(obtenerUsuarioNombre(insidenciaHistorico.getUsuarioIdUsuario().intValue()));
            insidenciaHistorico.setDocenteNombre(obtenerDocenteNombre(insidenciaHistorico.getDocenteIdDocente()));
            insidenciaHistorico.setPlanAcademicoNombre(obtenerPlanAcademicoNombre(insidenciaHistorico.getPlanAcademicoIdPlanAcademico()));
            return Optional.of(insidenciaHistorico);
        }
        return Optional.empty();
    }

    @Override
    public void eliminarPorId(Integer id) {
        insidenciaHistoricoRepository.deleteById(id);
    }


    private String obtenerUsuarioNombre(Integer idUsuario) {
        try {
            AuthUser authUser = usuarioFeign.buscarUsuario(idUsuario).getBody();
            if (authUser != null) {
                return authUser.getUser();
            }
        } catch (Exception e) {
            return "Usuario no encontrado";
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

    private InsidenciaHistorico enriquecer(InsidenciaHistorico i) {
        // setUsuarioNombre
        try {
            AuthUser u = usuarioFeign.buscarUsuario(i.getUsuarioIdUsuario().intValue())
                    .getBody();
            i.setUsuarioNombre(u != null ? u.getUser() : "Usuario no encontrado");
        } catch (Exception e) {
            i.setUsuarioNombre("Servicio no disponible de usuario");
        }
        // setDocenteNombre
        try {
            DocenteDto d = docenteFeing.buscarDocente(i.getDocenteIdDocente())
                    .getBody();
            i.setDocenteNombre(d != null ? d.getNombre() : "Docente no encontrado");
        } catch (Exception e) {
            i.setDocenteNombre("Servicio no disponible de docente");
        }
        // setPlanAcademicoNombre
        try {
            PlanAcademicoDto p = planAcademicoFeing
                    .buscarPlanAcademico(i.getPlanAcademicoIdPlanAcademico())
                    .getBody();
            i.setPlanAcademicoNombre(p != null
                    ? p.getNombrePlan()
                    : "Plan académico no encontrado");
        } catch (Exception e) {
            i.setPlanAcademicoNombre("Servicio no disponible de plan acad.");
        }
        return i;
    }

    @Override
    public List<InsidenciaHistorico> listarPorUsuario(Long usuarioId) {
        return insidenciaHistoricoRepository.findByUsuarioIdUsuario(usuarioId)
                .stream().map(this::enriquecer).collect(Collectors.toList());
    }

    @Override
    public List<InsidenciaHistorico> listarPorFechaPorDia(LocalDate desde, LocalDate hasta) {
        return List.of();
    }

    @Override
    public List<InsidenciaHistorico> listarPorFecha(LocalDateTime desde, LocalDateTime hasta) {
        return insidenciaHistoricoRepository.findByFechaRegistroBetween(desde, hasta)
                .stream().map(this::enriquecer).collect(Collectors.toList());
    }

    @Override
    public List<InsidenciaHistorico> buscarPorTitulo(String titulo) {
        return insidenciaHistoricoRepository.findByTituloContainingIgnoreCase(titulo)
                .stream().map(this::enriquecer).collect(Collectors.toList());
    }

    @Override
    public List<InsidenciaHistorico> listarPorUsuarioYFecha(Long usuarioId, LocalDateTime desde, LocalDateTime hasta) {
        return insidenciaHistoricoRepository
                .findByUsuarioIdUsuarioAndFechaRegistroBetween(usuarioId, desde, hasta)
                .stream().map(this::enriquecer).collect(Collectors.toList());
    }

    @Override
    public List<InsidenciaHistorico> listarPorUsuarioYFechaPorDia(Long usuarioId, LocalDate desde, LocalDate hasta) {
        LocalDateTime inicio = desde.atStartOfDay();
        LocalDateTime fin = hasta.atTime(LocalTime.MAX);

        return insidenciaHistoricoRepository
                .findByUsuarioIdUsuarioAndFechaRegistroBetween(usuarioId, inicio, fin)
                .stream().map(this::enriquecer).collect(Collectors.toList());
    }
}