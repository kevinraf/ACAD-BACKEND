package com.example.ms_kajita.controller;

import com.example.ms_kajita.entity.LibretaNotas;
import com.example.ms_kajita.service.LibretaNotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libretasNotas") // Ruta base para los endpoints de LibretaNotas
public class LibretaNotasController {

    @Autowired
    private LibretaNotasService libretaNotasService;

    @GetMapping
    public ResponseEntity<List<LibretaNotas>> listarTodas() {
        List<LibretaNotas> libretas = libretaNotasService.listar();
        return ResponseEntity.ok(libretas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibretaNotas> obtenerPorId(@PathVariable Integer id) {
        Optional<LibretaNotas> libretaOptional = libretaNotasService.listarPorId(id);
        return libretaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LibretaNotas> guardar(@RequestBody LibretaNotas libretaNotas) {
        LibretaNotas nuevaLibretaNotas = libretaNotasService.guardar(libretaNotas);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaLibretaNotas); // Código 201 Created es más apropiado para POST
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibretaNotas> actualizar(@PathVariable Integer id, @RequestBody LibretaNotas libretaNotas) {
        if (!libretaNotasService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libretaNotas.setIdLibretaNotas(id); // Asumo que el campo ID de LibretaNotas es 'idLibretaNotas'
        LibretaNotas libretaActualizada = libretaNotasService.actualizar(libretaNotas);
        return ResponseEntity.ok(libretaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!libretaNotasService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libretaNotasService.eliminarPorId(id);
        return ResponseEntity.noContent().build(); // 204 No Content para eliminaciones exitosas
    }
}