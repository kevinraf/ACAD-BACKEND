package com.academia.acadcargaacademica.Controlador;


import com.academia.acadcargaacademica.Entidad.Docente;
import com.academia.acadcargaacademica.Servicio.DocenteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/docentes")
public class DocenteControlador {
    @Autowired
    private DocenteServicio servicio;

    // Listar todo
    @GetMapping
    public ResponseEntity<List<Docente>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    // Buscar  ID
    @GetMapping("/{id}")
    public ResponseEntity<Docente> buscar(@PathVariable Long id) {
        Optional<Docente> instancia = servicio.buscar(id);
        return instancia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Guardar
    @PostMapping
    public ResponseEntity<Docente> guardar(@RequestBody Docente instancia) {
        Docente nuevo = servicio.guardar(instancia);
        return ResponseEntity.ok(nuevo);
    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<Docente> modificar(@PathVariable Long id, @RequestBody Docente instancia) {
        Docente actualizado = servicio.modificar(id, instancia);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
