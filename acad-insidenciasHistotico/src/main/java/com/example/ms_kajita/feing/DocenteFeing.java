package com.example.ms_kajita.feing;

import com.example.ms_kajita.dto.DocenteDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "acad-docente-service", path = "/docentes")
public interface DocenteFeing {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "DocentePorIdCB", fallbackMethod = "fallbackDocenteById")
    public ResponseEntity<DocenteDto> buscarDocente(@PathVariable Integer id);

    default ResponseEntity<DocenteDto> fallbackDocenteById(Integer id, Exception e) {
        DocenteDto docenteDto = new DocenteDto();
        docenteDto.setNombre("Servicio no disponible de docente");
        return ResponseEntity.ok(docenteDto);
    }
}
