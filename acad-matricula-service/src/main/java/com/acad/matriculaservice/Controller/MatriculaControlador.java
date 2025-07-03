package com.acad.matriculaservice.Controller;

import com.acad.matriculaservice.Entity.Matricula;
import com.acad.matriculaservice.Service.MatriculaServicio;
import com.acad.matriculaservice.Util.MatriculaPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaControlador {

    @Autowired
    private MatriculaServicio matriculaServicio;

    // Listar todas las matrículas
    @GetMapping
    public ResponseEntity<List<Matricula>> listar() {
        return new ResponseEntity<>(matriculaServicio.listar(), HttpStatus.OK);
    }

    // Buscar una matrícula por ID
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscar(@PathVariable Long id) {
        Matricula matricula = matriculaServicio.buscar(id);
        if (matricula != null) {
            return new ResponseEntity<>(matricula, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Guardar una nueva matrícula
    @PostMapping
    public ResponseEntity<Matricula> guardar(@RequestBody Matricula matricula) {
        Matricula nuevaMatricula = matriculaServicio.guardar(matricula);
        return new ResponseEntity<>(nuevaMatricula, HttpStatus.CREATED);
    }

    // Actualizar una matrícula existente
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> actualizar(@PathVariable Long id, @RequestBody Matricula matricula) {
        Matricula existente = matriculaServicio.buscar(id);
        if (existente != null) {
            matricula.setIdMatricula(id); // Mantener ID existente
            Matricula matriculaActualizada = matriculaServicio.actualizar(matricula);
            return new ResponseEntity<>(matriculaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una matrícula por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        matriculaServicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Exportar a PDF
    @GetMapping("/exportar-pdf")
    public ResponseEntity<byte[]> exportarPdf() throws IOException {
        List<Matricula> matriculas = matriculaServicio.listar();
        ByteArrayInputStream pdf = MatriculaPdfGenerator.generarPdf(matriculas);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=matriculas.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }
}
