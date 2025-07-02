package com.example.ms_kajita.service;

import com.example.ms_kajita.dto.UsuarioEstadoStatsDto;
import com.example.ms_kajita.entity.Asistencia;

import java.time.LocalDate;
import java.time.LocalDateTime;


import java.util.List;
import java.util.Optional;

public interface AsistenciaService {


    List<Asistencia> listar();

    Asistencia guardar(Asistencia asistencia);

    Asistencia actualizar(Asistencia asistencia);

    Optional<Asistencia> listarPorId(Integer id);

    public void eliminarPorId(Integer id);


    List<Asistencia> listarPorNombreUsuario(String userName);

    List<Asistencia> listarPorFechaPorDia(LocalDate desde, LocalDate hasta);


    List<Asistencia> listarPorNombreCurso(String nombreCurso);

    List<UsuarioEstadoStatsDto> usuariosConMasFaltas();

    List<UsuarioEstadoStatsDto> usuariosConMasTardanzas();

    List<UsuarioEstadoStatsDto> usuariosConMasPresentes();
}