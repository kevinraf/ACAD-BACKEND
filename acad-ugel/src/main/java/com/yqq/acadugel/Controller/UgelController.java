package com.yqq.acadugel.Controller;

import com.yqq.acadugel.Entity.Ugel;
import com.yqq.acadugel.Servicio.UgelServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ugeles")
@CrossOrigin("*")
public class UgelController {

    @Autowired
    private UgelServicio ugelServicio;

    @GetMapping
    public List<Ugel> listar() {
        return ugelServicio.findAll();
    }

    @GetMapping("/{id}")
    public Ugel obtenerPorId(@PathVariable Integer id) {
        return ugelServicio.findById(id);
    }

    @PostMapping
    public Ugel crear(@RequestBody Ugel ugel) {
        return ugelServicio.save(ugel);
    }

    @PutMapping("/{id}")
    public Ugel actualizar(@PathVariable Integer id, @RequestBody Ugel ugel) {
        Ugel existente = ugelServicio.findById(id);
        if (existente != null) {
            existente.setNombreDeLaUgel(ugel.getNombreDeLaUgel());
            return ugelServicio.save(existente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ugelServicio.deleteById(id);
    }
}