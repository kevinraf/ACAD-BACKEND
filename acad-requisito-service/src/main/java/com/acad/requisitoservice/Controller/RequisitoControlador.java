package com.acad.requisitoservice.Controller;

import com.acad.requisitoservice.Entity.Requisito;
import com.acad.requisitoservice.Service.RequisitoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisitos")
public class RequisitoControlador {

    @Autowired
    private RequisitoServicio requisitoServicio;

    // Listar todos los requisitos
    @GetMapping
    public ResponseEntity<List<Requisito>> listar() {
        return new ResponseEntity<>(requisitoServicio.listar(), HttpStatus.OK);
    }

    // Buscar un requisito por ID
    @GetMapping("/{id}")
    public ResponseEntity<Requisito> buscar(@PathVariable Long id) {
        Requisito requisito = requisitoServicio.buscar(id);
        if (requisito != null) {
            return new ResponseEntity<>(requisito, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Guardar un nuevo requisito
    @PostMapping
    public ResponseEntity<Requisito> guardar(@RequestBody Requisito requisito) {
        Requisito nuevoRequisito = requisitoServicio.guardar(requisito);
        return new ResponseEntity<>(nuevoRequisito, HttpStatus.CREATED);
    }

    // Actualizar un requisito existente
    @PutMapping("/{id}")
    public ResponseEntity<Requisito> actualizar(@PathVariable Long id, @RequestBody Requisito requisito) {
        Requisito existente = requisitoServicio.buscar(id);
        if (existente != null) {
            requisito.setIdRequisitos(id); // Mantener ID existente
            Requisito requisitoActualizado = requisitoServicio.actualizar(requisito);
            return new ResponseEntity<>(requisitoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un requisito por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        requisitoServicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
