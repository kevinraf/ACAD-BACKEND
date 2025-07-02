package com.acad.apoderadoservice.Controller;

import com.acad.apoderadoservice.Entity.Apoderado;
import com.acad.apoderadoservice.Service.ApoderadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apoderados")
public class ApoderadoControlador {

    @Autowired
    private ApoderadoServicio apoderadoServicio;

    // Listar todos los apoderados
    @GetMapping
    public ResponseEntity<List<Apoderado>> listar() {
        return ResponseEntity.ok(apoderadoServicio.listar());
    }

    // Buscar apoderado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Apoderado> buscar(@PathVariable Long id) {
        Optional<Apoderado> apoderado = apoderadoServicio.buscar(id);
        return apoderado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Guardar nuevo apoderado
    @PostMapping
    public ResponseEntity<Apoderado> guardar(@RequestBody Apoderado apoderado) {
        Apoderado nuevo = apoderadoServicio.guardar(apoderado);
        return ResponseEntity.ok(nuevo);
    }

    // Modificar apoderado existente
    @PutMapping("/{id}")
    public ResponseEntity<Apoderado> modificar(@PathVariable Long id, @RequestBody Apoderado apoderado) {
        Apoderado actualizado = apoderadoServicio.modificar(id, apoderado);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar apoderado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        apoderadoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
