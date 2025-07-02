package com.acad.matriculaservice.Feign;

import com.acad.matriculaservice.Dto.RequisitosDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "acad-requisito-service", path = "/requisitos")
public interface RequisitosFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "requisitoBuscarPorIdCB", fallbackMethod = "fallbackBuscarRequisitoById")
    ResponseEntity<RequisitosDto> buscarRequisitos(@PathVariable Long id);

    @PostMapping
    @CircuitBreaker(name = "requisitoCrearCB", fallbackMethod = "fallbackCrearRequisito")
    ResponseEntity<RequisitosDto> crearRequisitos(@RequestBody RequisitosDto dto);

    // Fallback para buscar requisito por ID
    default ResponseEntity<RequisitosDto> fallbackBuscarRequisitoById(Long id, Throwable e) {
        RequisitosDto requisitosDto = new RequisitosDto();
        requisitosDto.setDniEstudiante("Servicio de requisitos no disponible");
        return ResponseEntity.ok(requisitosDto);
    }

    // Fallback para crear un nuevo requisito
    default ResponseEntity<RequisitosDto> fallbackCrearRequisito(RequisitosDto dto, Throwable e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
