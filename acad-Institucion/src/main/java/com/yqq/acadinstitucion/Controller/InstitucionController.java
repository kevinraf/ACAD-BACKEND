package com.yqq.acadinstitucion.Controller;

import com.yqq.acadinstitucion.Dto.InstitucionDto;
import com.yqq.acadinstitucion.Entity.Institucion;
import com.yqq.acadinstitucion.Servicio.InstitucionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instituciones")
@RequiredArgsConstructor
public class InstitucionController {

    private final InstitucionServicio servicio;

    @PostMapping
    public ResponseEntity<InstitucionDto> save(@RequestBody Institucion institucion) {
        return ResponseEntity.ok(servicio.save(institucion));
    }

    @GetMapping
    public ResponseEntity<List<InstitucionDto>> getAll() {
        return ResponseEntity.ok(servicio.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitucionDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.getById(id));
    }
}
