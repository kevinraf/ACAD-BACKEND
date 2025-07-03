package com.example.ms_kajita.controller;

import com.example.ms_kajita.dto.UsuarioEstadoStatsDto;
import com.example.ms_kajita.entity.Asistencia;
import com.example.ms_kajita.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

    // Listar todas las asistencias
    @GetMapping
    public ResponseEntity<List<Asistencia>> listar() {
        List<Asistencia> asistencias = asistenciaService.listar();
        return new ResponseEntity<>(asistencias, HttpStatus.OK);
    }

    // Obtener asistencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> buscar(@PathVariable Integer id) {
        Optional<Asistencia> asistenciaOpt = asistenciaService.listarPorId(id);
        return asistenciaOpt
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear nueva asistencia
    @PostMapping
    public ResponseEntity<Asistencia> guardar(@RequestBody Asistencia asistencia) {
        Asistencia nueva = asistenciaService.guardar(asistencia);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // Actualizar asistencia existente
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizar(@PathVariable Integer id,
                                                 @RequestBody Asistencia asistencia) {
        Optional<Asistencia> existente = asistenciaService.listarPorId(id);
        if (existente.isPresent()) {
            asistencia.setIdAsistencia(id);
            Asistencia actualizada = asistenciaService.actualizar(asistencia);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar asistencia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Optional<Asistencia> existente = asistenciaService.listarPorId(id);
        if (existente.isPresent()) {
            asistenciaService.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Filtrar por nombre de usuario
    @GetMapping("/buscar-usuario")
    public ResponseEntity<List<Asistencia>> buscarPorUsuario(
            @RequestParam String userName) {
        List<Asistencia> list = asistenciaService.listarPorNombreUsuario(userName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Filtrar por nombre de curso
    @GetMapping("/buscar-curso")
    public ResponseEntity<List<Asistencia>> buscarPorCurso(
            @RequestParam String nombre) {
        List<Asistencia> list = asistenciaService.listarPorNombreCurso(nombre);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Filtrar por rango de fechas
    @GetMapping("/buscar-fecha")
    public ResponseEntity<List<Asistencia>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        List<Asistencia> list = asistenciaService.listarPorFechaPorDia(desde, hasta);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Estadísticas: faltas
    @GetMapping("/faltas")
    public ResponseEntity<List<UsuarioEstadoStatsDto>> faltas() {
        List<UsuarioEstadoStatsDto> stats = asistenciaService.usuariosConMasFaltas();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    // Estadísticas: tardanzas
    @GetMapping("/tardanzas")
    public ResponseEntity<List<UsuarioEstadoStatsDto>> tardanzas() {
        List<UsuarioEstadoStatsDto> stats = asistenciaService.usuariosConMasTardanzas();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    // Estadísticas: presentes
    @GetMapping("/presentes")
    public ResponseEntity<List<UsuarioEstadoStatsDto>> presentes() {
        List<UsuarioEstadoStatsDto> stats = asistenciaService.usuariosConMasPresentes();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
