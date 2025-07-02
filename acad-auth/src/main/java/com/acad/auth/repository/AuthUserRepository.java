package com.acad.auth.repository;

import com.acad.auth.entity.AuthUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthUserRepository extends JpaRepository<AuthUser,Integer> {
    Optional<AuthUser> findByUserName(String userName);
}
