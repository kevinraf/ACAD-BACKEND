package com.example.ms_kajita.util; // Asegúrate de que este sea el paquete correcto

import com.example.ms_kajita.entity.LibretaNotas;
import com.example.ms_kajita.repository.libretaNotasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LibretaNotasSeeder implements CommandLineRunner {

    private final libretaNotasRepository libretaNotasRepository;

    // Inyección de dependencias a través del constructor
    public LibretaNotasSeeder(libretaNotasRepository libretaNotasRepository) {
        this.libretaNotasRepository = libretaNotasRepository;
    }

    @Override
    public void run(String... args) {
        // Solo inserta datos si la tabla está vacía
        if (libretaNotasRepository.count() == 0) {

            LibretaNotas l1 = new LibretaNotas();

            l1.setIdMatricula(101);
            l1.setIdNotasCompetencia(1);

            LibretaNotas l2 = new LibretaNotas();
            l2.setIdMatricula(102);
            l2.setIdNotasCompetencia(2);

            LibretaNotas l3 = new LibretaNotas();
            l3.setIdMatricula(103);
            l3.setIdNotasCompetencia(3);

            // Guardar las entidades en la base de datos
            libretaNotasRepository.save(l1);
            libretaNotasRepository.save(l2);
            libretaNotasRepository.save(l3);

            System.out.println("Datos de libretas de notas insertados correctamente.");
        } else {
            System.out.println("Las libretas de notas ya existen, no se insertaron datos.");
        }
    }
}