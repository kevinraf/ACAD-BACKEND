package com.acad.auth.service.impl;



import com.acad.auth.dto.AuthUserDto;
import com.acad.auth.entity.AuthUser;
import com.acad.auth.entity.TokenDto;
import com.acad.auth.repository.AuthUserRepository;
import com.acad.auth.security.JwtProvider;
import com.acad.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;




@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;


    @Override
    public AuthUser save(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isPresent())
            return null;
        String password = passwordEncoder.encode(authUserDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .build();




        return authUserRepository.save(authUser);
    }




    @Override
    public TokenDto login(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (!user.isPresent())
            return null;
        if (passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }




    @Override
    public TokenDto validate(String token) {
        if (!jwtProvider.validate(token))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if (!authUserRepository.findByUserName(username).isPresent())
            return null;
        return new TokenDto(token);
    }


    public AuthUserServiceImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public List<AuthUser> getAllUsers() {
        return authUserRepository.findAll(); // Método heredado de JpaRepository
    }

    @Override
    public Optional<AuthUser> getUserById(int id) {
        return authUserRepository.findById(id); // Método heredado de JpaRepository
    }

    @Override
    public AuthUser saveUser(AuthUser authUser) {
        return authUserRepository.save(authUser); // Método heredado de JpaRepository (para guardar y actualizar)
    }

    @Override
    public void deleteUser(int id) {
        authUserRepository.deleteById(id); // Método heredado de JpaRepository
    }

}
