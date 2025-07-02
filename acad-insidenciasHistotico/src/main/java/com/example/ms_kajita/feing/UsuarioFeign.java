package com.example.ms_kajita.feing;

import com.example.ms_kajita.dto.AuthUser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-auth-service", path = "/auth")

public interface UsuarioFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "UsuarioPorIdCB", fallbackMethod = "fallbackUsuarioById")
    public ResponseEntity<AuthUser> buscarUsuario(@PathVariable Integer id);

    default ResponseEntity<AuthUser> fallbackUsuarioById(Integer id, Exception e) {
        AuthUser authUser = new AuthUser();
        authUser.setUser("Servicio no disponible de usuario");
        return ResponseEntity.ok(authUser);
    }
}