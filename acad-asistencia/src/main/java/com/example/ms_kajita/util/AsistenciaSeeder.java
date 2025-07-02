package com.example.ms_kajita.util;

import com.example.ms_kajita.entity.Asistencia;
import com.example.ms_kajita.repository.AsistenciaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random; // Necesitamos esto para números aleatorios

@Component
public class AsistenciaSeeder implements CommandLineRunner {

    private final AsistenciaRepository asistenciaRepository;
    private final Random random = new Random(); // Instancia de Random

    // Estados de asistencia posibles
    private static final String[] ESTADOS = {"PRESENTE", "FALTA", "TARDANZA"};

    public AsistenciaSeeder(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    public void run(String... args) {
        if (asistenciaRepository.count() == 0) {
            System.out.println("Insertando datos de asistencias...");

            for (int i = 0; i < 10; i++) { // Bucle para insertar 10 registros
                Asistencia asistencia = new Asistencia();

                // 1. Generar fecha de registro aleatoria (ej. dentro de los últimos 30 días)
                LocalDateTime randomDate = LocalDateTime.now()
                        .minusDays(random.nextInt(30)) // Días aleatorios atrás (0 a 29)
                        .minusHours(random.nextInt(24)) // Horas aleatorias
                        .minusMinutes(random.nextInt(60)) // Minutos aleatorios
                        .minusSeconds(random.nextInt(60)); // Segundos aleatorios
                asistencia.setFechaRegistroAsistencia(randomDate);

                // 2. Generar IDs de usuario, curso, docente, planAcademico aleatorios (puedes ajustar los rangos)
                asistencia.setUsuarioIdUsuario(1L + random.nextInt(5)); // Usuarios del 1 al 5
                asistencia.setCursoIdCurso(1 + random.nextInt(3));    // Cursos del 1 al 3
                asistencia.setDocenteIdDocente(4 + random.nextInt(3)); // Docentes del 4 al 6
                asistencia.setPlanAcademicoIdPlanAcademico(7 + random.nextInt(3)); // Planes del 7 al 9

                // 3. Seleccionar un estado de asistencia aleatorio
                String randomEstado = ESTADOS[random.nextInt(ESTADOS.length)];
                asistencia.setEstadoAsistencia(randomEstado);

                asistenciaRepository.save(asistencia);
            }

            System.out.println("10 datos de asistencias aleatorios insertados correctamente.");
        } else {
            System.out.println("Las asistencias ya existen, no se insertaron datos.");
        }
    }
}