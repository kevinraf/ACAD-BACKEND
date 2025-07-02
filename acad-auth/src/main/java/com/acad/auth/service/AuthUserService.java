package com.acad.auth.service;


import com.acad.auth.dto.AuthUserDto;
import com.acad.auth.entity.AuthUser;
import com.acad.auth.entity.TokenDto;

import java.util.List;
import java.util.Optional;

public interface AuthUserService {
    public AuthUser save(AuthUserDto authUserDto);


    public TokenDto login(AuthUserDto authUserDto);


    public TokenDto validate(String token);

    List<AuthUser> getAllUsers();

    Optional<AuthUser> getUserById(int id);

    AuthUser saveUser(AuthUser authUser);

    void deleteUser(int id);
}

