package com.example.ms_kajita.feing;

import com.example.ms_kajita.dto.PlanAcademicoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-planAcademico-service", path = "/planAcademicos")

public interface PlanAcademicoFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "PlanAcademicoPorIdCB", fallbackMethod = "fallbackPlanAcademicoById")
    public ResponseEntity<PlanAcademicoDto> buscarPlanAcademico(@PathVariable Integer id);

    default ResponseEntity<PlanAcademicoDto> fallbackPlanAcademicoById(Integer id, Exception e) {
        PlanAcademicoDto planAcademicoDto = new PlanAcademicoDto();
        planAcademicoDto.setNombrePlan("Servicio no disponible de plan academico");
        return ResponseEntity.ok(planAcademicoDto);
    }

}
