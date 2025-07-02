package com.acad.requisitoservice.Feign;

import com.acad.requisitoservice.Dto.ApoderadoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "acad-apoderado-service", path = "/apoderados")
public interface ApoderadoFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "apoderadoListarPorIdCB", fallbackMethod = "fallbackApoderadoById")
    ResponseEntity<ApoderadoDto> buscarApoderado(@PathVariable Long id);

    @PostMapping
    @CircuitBreaker(name = "apoderadoCrearCB", fallbackMethod = "fallbackCrearApoderado")
    ResponseEntity<ApoderadoDto> crearApoderado(@RequestBody ApoderadoDto dto);


    default ResponseEntity<ApoderadoDto> fallbackApoderadoById(Long id, Throwable e) {
        ApoderadoDto apoderadoDto = new ApoderadoDto();
        apoderadoDto.setNombres("Servicio de apoderado no disponible");
        return ResponseEntity.ok(apoderadoDto);
    }

    default ResponseEntity<ApoderadoDto> fallbackCrearApoderado(ApoderadoDto dto, Throwable e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
