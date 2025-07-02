package com.example.ms_kajita.feing;

import com.example.ms_kajita.dto.CursoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-curso-service", path = "/cursos")

public interface CursoFeing {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "CursoPorIdCB", fallbackMethod = "fallbackCursoById")
    public ResponseEntity<CursoDto> buscarCurso(@PathVariable Integer id);

    default ResponseEntity<CursoDto> fallbackCursoById(Integer id, Exception e) {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setNombre("Servicio no disponible de curso");
        return ResponseEntity.ok(cursoDto);
    }
}
