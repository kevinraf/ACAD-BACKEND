package com.yqq.acadinstitucion.Feign;

import com.yqq.acadinstitucion.Dto.SedeDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "acad-sede", path = "/sedes")
public interface SedeFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "sedeCB", fallbackMethod = "fallbackSedeById")
    SedeDto getSedeById(@PathVariable Long id);

    default SedeDto fallbackSedeById(Long id, Throwable throwable) {
        SedeDto fallback = new SedeDto();
        fallback.setIdSede(id);
        fallback.setNombreSede("Sede no disponible");
        return fallback;
    }
}

