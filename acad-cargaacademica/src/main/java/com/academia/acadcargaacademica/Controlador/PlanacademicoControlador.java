package com.academia.acadcargaacademica.Controlador;


import com.academia.acadcargaacademica.Entidad.Planacademico;
import com.academia.acadcargaacademica.Servicio.PlanacademicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/planAcademicos")
public class PlanacademicoControlador {
    @Autowired
    private PlanacademicoServicio servicio;

    // Listar todo
    @GetMapping
    public ResponseEntity<List<Planacademico>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    // Buscar  ID
    @GetMapping("/{id}")
    public ResponseEntity<Planacademico> buscar(@PathVariable Long id) {
        Optional<Planacademico> instancia = servicio.buscar(id);
        return instancia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Guardar
    @PostMapping
    public ResponseEntity<Planacademico> guardar(@RequestBody Planacademico instancia) {
        Planacademico nuevo = servicio.guardar(instancia);
        return ResponseEntity.ok(nuevo);
    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<Planacademico> modificar(@PathVariable Long id, @RequestBody Planacademico instancia) {
        Planacademico actualizado = servicio.modificar(id, instancia);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
