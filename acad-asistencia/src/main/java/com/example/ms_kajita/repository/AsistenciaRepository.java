package com.example.ms_kajita.repository;

import com.example.ms_kajita.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {


    // 3. Listar por rango de fechas
    List<Asistencia> findByFechaRegistroAsistenciaBetween(
            LocalDateTime desde, LocalDateTime hasta);

    // 4. Estadísticas: conteo por usuario y estado
    @Query("SELECT a.usuarioIdUsuario AS usuarioId, a.estadoAsistencia AS estado, COUNT(a) AS cantidad "
            + "FROM Asistencia a "
            + "GROUP BY a.usuarioIdUsuario, a.estadoAsistencia")
    List<UsuarioEstadoCount> countByUsuarioAndEstado();

    interface UsuarioEstadoCount {
        Long getUsuarioId();

        String getEstado();

        Long getCantidad();
    }
    List<Asistencia> findByEstadoAsistenciaIgnoreCase(String estado);
}