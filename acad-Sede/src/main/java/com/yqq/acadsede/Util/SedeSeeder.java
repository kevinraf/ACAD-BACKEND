package com.yqq.acadsede.Util;

import com.yqq.acadsede.Entity.Sede;
import com.yqq.acadsede.Repository.SedeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SedeSeeder implements CommandLineRunner {

    private final SedeRepository sedeRepository;

    public SedeSeeder(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @Override
    public void run(String... args) {
        if (sedeRepository.count() == 0) {
            // Insertar sedes de ejemplo
            Sede sede1 = new Sede(null, "Sede Puno");
            Sede sede2 = new Sede(null, "Sede Juliaca");


            sedeRepository.save(sede1);
            sedeRepository.save(sede2);


            System.out.println("Datos de Sede insertados correctamente en H2.");
        } else {
            System.out.println("Los datos de Sede ya existen en H2.");
        }
    }
}
