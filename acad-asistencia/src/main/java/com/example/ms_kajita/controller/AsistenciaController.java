package com.example.ms_kajita.controller;

import com.example.ms_kajita.dto.UsuarioEstadoStatsDto;
import com.example.ms_kajita.entity.Asistencia;
import com.example.ms_kajita.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<Asistencia>> listarTodas() {
        List<Asistencia> asistencias = asistenciaService.listar();
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> obtenerPorId(@PathVariable Integer id) {
        Optional<Asistencia> asistenciaOptional = asistenciaService.listarPorId(id);
        return asistenciaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asistencia> guardar(@RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = asistenciaService.guardar(asistencia);
        return ResponseEntity.ok(nuevaAsistencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizar(@PathVariable Integer id, @RequestBody Asistencia asistencia) {
        if (!asistenciaService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        asistencia.setIdAsistencia(id); // Asegura que se actualice el registro correcto
        Asistencia asistenciaActualizada = asistenciaService.actualizar(asistencia);
        return ResponseEntity.ok(asistenciaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!asistenciaService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        asistenciaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
    // --- FILTROS ---

    @GetMapping("/buscar-usuario")
    public ResponseEntity<List<Asistencia>> listarPorNombreUsuario(
            @RequestParam String userName) {
        List<Asistencia> list = asistenciaService.listarPorNombreUsuario(userName);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscar-curso")
    public ResponseEntity<List<Asistencia>> listarPorNombreCurso(
            @RequestParam String nombre) {
        List<Asistencia> list = asistenciaService.listarPorNombreCurso(nombre);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscar-fecha")
    public ResponseEntity<List<Asistencia>> listarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

        List<Asistencia> list = asistenciaService.listarPorFechaPorDia(desde, hasta);
        return ResponseEntity.ok(list);
    }

    // --- ESTADÍSTICAS ---

    @GetMapping("/faltas")
    public ResponseEntity<List<UsuarioEstadoStatsDto>> usuariosConMasFaltas() {
        List<UsuarioEstadoStatsDto> stats = asistenciaService.usuariosConMasFaltas();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/tardanzas")
    public ResponseEntity<List<UsuarioEstadoStatsDto>> usuariosConMasTardanzas() {
        List<UsuarioEstadoStatsDto> stats = asistenciaService.usuariosConMasTardanzas();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/presentes")
    public ResponseEntity<List<UsuarioEstadoStatsDto>> usuariosConMasPresentes() {
        List<UsuarioEstadoStatsDto> stats = asistenciaService.usuariosConMasPresentes();
        return ResponseEntity.ok(stats);
    }
}
