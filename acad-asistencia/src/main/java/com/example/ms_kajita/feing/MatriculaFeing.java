package com.example.ms_kajita.feing;

import com.example.ms_kajita.dto.MatriculaDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-matricula-service", path = "/matriculas")

public interface MatriculaFeing {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "MatriculaPorIdCB", fallbackMethod = "fallbackMatriculaById")
    public ResponseEntity<MatriculaDto> buscarMatricula(@PathVariable Integer id);

    default ResponseEntity<MatriculaDto> fallbackMatriculaById(Integer id, Exception e) {
        MatriculaDto matriculaDto = new MatriculaDto();
        matriculaDto.setCodigoMatricula("Servicio no disponible de curso");
        return ResponseEntity.ok(matriculaDto);
    }
}
