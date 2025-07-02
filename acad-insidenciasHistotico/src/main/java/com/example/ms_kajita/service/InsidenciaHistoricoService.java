package com.example.ms_kajita.service;

import com.example.ms_kajita.entity.InsidenciaHistorico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InsidenciaHistoricoService {


    List<InsidenciaHistorico> listar();

    InsidenciaHistorico guardar(InsidenciaHistorico insidenciaHistorico);

    InsidenciaHistorico actualizar(InsidenciaHistorico insidenciaHistorico);

    Optional<InsidenciaHistorico> listarPorId(Integer id);

    public void eliminarPorId(Integer id);


    // Nuevos:
    List<InsidenciaHistorico> listarPorUsuario(Long usuarioId);


    List<InsidenciaHistorico> listarPorFechaPorDia(LocalDate desde, LocalDate hasta);


    List<InsidenciaHistorico> listarPorFecha(LocalDateTime desde, LocalDateTime hasta);

    List<InsidenciaHistorico> buscarPorTitulo(String titulo);


    List<InsidenciaHistorico> listarPorUsuarioYFecha(Long usuarioId, LocalDateTime desde, LocalDateTime hasta);

    List<InsidenciaHistorico> listarPorUsuarioYFechaPorDia(Long usuarioId, LocalDate desde, LocalDate hasta);
}