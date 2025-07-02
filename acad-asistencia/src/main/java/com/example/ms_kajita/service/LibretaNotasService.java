package com.example.ms_kajita.service;

import com.example.ms_kajita.entity.LibretaNotas;

import java.util.List;
import java.util.Optional;

public interface LibretaNotasService {


    List<LibretaNotas> listar();

    LibretaNotas guardar(LibretaNotas libretaNotas);

    LibretaNotas actualizar(LibretaNotas libretaNotas);


    Optional<LibretaNotas> listarPorId(Integer id);

    public void eliminarPorId(Integer id);


}