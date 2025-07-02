package com.academia.acadcargaacademica.Controlador;

import com.academia.acadcargaacademica.Entidad.Curso;
import com.academia.acadcargaacademica.Servicio.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoControlador {
    @Autowired
    private CursoServicio servicio;

    // Listar todo
    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    // Buscar  ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Long id) {
        Optional<Curso> instancia = servicio.buscar(id);
        return instancia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Guardar
    @PostMapping
    public ResponseEntity<Curso> guardar(@RequestBody Curso instancia) {
        Curso nuevo = servicio.guardar(instancia);
        return ResponseEntity.ok(nuevo);
    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<Curso> modificar(@PathVariable Long id, @RequestBody Curso instancia) {
        Curso actualizado = servicio.modificar(id, instancia);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
