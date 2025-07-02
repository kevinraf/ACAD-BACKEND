package com.acad.auth.controller;



import com.acad.auth.dto.AuthUserDto;
import com.acad.auth.entity.AuthUser;
import com.acad.auth.entity.TokenDto;
import com.acad.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {
        TokenDto tokenDto = authUserService.login(authUserDto);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {
        TokenDto tokenDto = authUserService.validate(token);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto authUserDto) {
        AuthUser authUser = authUserService.save(authUserDto);
        if (authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }

    @GetMapping
    public ResponseEntity<List<AuthUser>> getAllAuthUsers() {
        List<AuthUser> users = authUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthUserDto> getUsuarioDtoById(@PathVariable int id) {
        return authUserService.getUserById(id)
                .map(u -> {
                    AuthUserDto dto = new AuthUserDto();
                    dto.setId(u.getId());
                    dto.setUserName(u.getUserName());   // ajusta a tu propiedad “user”
                    dto.setEstado(u.getEstado());
                    dto.setCargo(u.getCargo());
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<AuthUser> updateAuthUser(@PathVariable int id, @RequestBody AuthUser authUser) {
        // Primero, verifica si el usuario existe
        Optional<AuthUser> existingUser = authUserService.getUserById(id);
        if (existingUser.isPresent()) {
            // Asegúrate de que el ID del objeto a guardar coincida con el ID del path
            authUser.setId(id);
            AuthUser updatedUser = authUserService.saveUser(authUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthUser(@PathVariable int id) {
        // Opcional: Verificar si el usuario existe antes de intentar eliminarlo
        if (authUserService.getUserById(id).isPresent()) {
            authUserService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 indica que la solicitud fue exitosa pero no hay contenido que devolver
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 si el usuario no existe
        }
    }
}
