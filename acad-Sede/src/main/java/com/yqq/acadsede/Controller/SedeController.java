package com.yqq.acadsede.Controller;

import com.yqq.acadsede.Entity.Sede;
import com.yqq.acadsede.Servicio.SedeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sedes")

public class SedeController {

    @Autowired
    private SedeServicio sedeServicio;

    @GetMapping
    public List<Sede> listar() {
        return sedeServicio.findAll();
    }

    @GetMapping("/{id}")
    public Sede obtenerPorId(@PathVariable Long id) {
        return sedeServicio.findById(id);
    }

    @PostMapping
    public Sede crear(@RequestBody Sede sede) {
        return sedeServicio.save(sede);
    }

    @PutMapping("/{id}")
    public Sede actualizar(@PathVariable Long id, @RequestBody Sede sede) {
        Sede existente = sedeServicio.findById(id);
        if (existente != null) {
            existente.setNombreSede(sede.getNombreSede());
            return sedeServicio.save(existente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        sedeServicio.deleteById(id);
    }
}