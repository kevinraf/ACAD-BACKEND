package com.yqq.acadinstitucion.Controller;

import com.yqq.acadinstitucion.Dto.InstitucionResponse;
import com.yqq.acadinstitucion.Entity.Institucion;
import com.yqq.acadinstitucion.Servicio.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institucion")
public class InstitucionController {

    @Autowired
    private InstitucionService institucionService;

    // Crear institución
    @PostMapping
    public Institucion crear(@RequestBody Institucion institucion) {
        return institucionService.save(institucion);
    }

    // Listar todas las instituciones
    @GetMapping
    public List<Institucion> listar() {
        return institucionService.listar();
    }

    // Buscar institución por ID
    @GetMapping("/{id}")
    public Institucion buscarPorId(@PathVariable Long id) {
        return institucionService.buscar(id);
    }

    // Actualizar institución
    @PutMapping
    public Institucion actualizar(@RequestBody Institucion institucion) {
        return institucionService.actualizar(institucion);
    }

    // Eliminar institución por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        institucionService.eliminar(id);
    }

    // Buscar instituciones por nombre (usando DTO + Feign)
    @GetMapping("/buscar")
    public List<InstitucionResponse> buscarPorNombre(@RequestParam String nombre) {
        return institucionService.findByNombre(nombre);
    }
}
