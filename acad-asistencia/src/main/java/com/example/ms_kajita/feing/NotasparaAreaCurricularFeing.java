package com.example.ms_kajita.feing;

import com.example.ms_kajita.dto.NotasparaAreaCurricularDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "acad-notasAreaCurricular-service", path = "/api/notasAreaCurriculars")
public interface NotasparaAreaCurricularFeing {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "notasparaAreaCurricularPorIdCB", fallbackMethod = "fallbackNotasparaAreaCurricularById")
    public ResponseEntity<NotasparaAreaCurricularDto> buscarnotasparaAreaCurricular(@PathVariable Integer id);

    default ResponseEntity<NotasparaAreaCurricularDto> fallbackNotasparaAreaCurricularById(Integer id, Exception e) {
        NotasparaAreaCurricularDto notasparaAreaCurricularDto = new NotasparaAreaCurricularDto();
        notasparaAreaCurricularDto.setNota("Servicio no disponible de nota");
        notasparaAreaCurricularDto.setNotaFinal("Servicio no disponible de nota final");
        notasparaAreaCurricularDto.setIdPlanAcademico("Servicio no disponible de plan academico");
        return ResponseEntity.ok(notasparaAreaCurricularDto);
    }
}
