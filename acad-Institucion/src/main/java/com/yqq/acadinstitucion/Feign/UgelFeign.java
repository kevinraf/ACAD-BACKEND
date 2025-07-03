package com.yqq.acadinstitucion.Feign;

import com.yqq.acadinstitucion.Dto.UgelDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "acad-ugel", path = "/ugeles")
public interface UgelFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "ugelCB", fallbackMethod = "fallbackUgelById")
    UgelDto getUgelById(@PathVariable Long id);

    default UgelDto fallbackUgelById(Long id, Throwable throwable) {
        UgelDto fallback = new UgelDto();
        fallback.setIdUgel(id);
        fallback.setNombreDeLaUgel("UGEL no disponible");
        return fallback;
    }
}
