package com.example.ms_kajita.repository;

import com.example.ms_kajita.entity.InsidenciaHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InsidenciaHistoricoRepository extends JpaRepository<InsidenciaHistorico, Integer> {

    // 1) Listar todas las incidencias de un usuario (por ID)
    List<InsidenciaHistorico> findByUsuarioIdUsuario(Long usuarioIdUsuario);

    // 2) Listar por rango de fecha
    List<InsidenciaHistorico> findByFechaRegistroBetween(LocalDateTime desde, LocalDateTime hasta);

    // 3) Listar por título (contenga texto)
    List<InsidenciaHistorico> findByTituloContainingIgnoreCase(String titulo);

    // 4) Listar por usuario + rango de fecha
    List<InsidenciaHistorico> findByUsuarioIdUsuarioAndFechaRegistroBetween(Long usuarioIdUsuario,
                                                  LocalDateTime desde,
                                                  LocalDateTime hasta);
}

