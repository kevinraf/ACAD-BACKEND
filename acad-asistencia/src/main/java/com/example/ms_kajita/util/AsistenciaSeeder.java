package com.example.ms_kajita.util;

import com.example.ms_kajita.entity.Asistencia;
import com.example.ms_kajita.repository.AsistenciaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class AsistenciaSeeder implements CommandLineRunner {

    private final AsistenciaRepository asistenciaRepository;
    private final Random random = new Random();

    // Estados de asistencia posibles
    private static final String[] ESTADOS = {"PRESENTE", "FALTA", "TARDANZA"};

    public AsistenciaSeeder(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    public void run(String... args) {
        if (asistenciaRepository.count() == 0) {
            System.out.println("Insertando datos de asistencias...");

            // Bucle para insertar 30 registros
            for (int i = 0; i < 30; i++) {
                Asistencia asistencia = new Asistencia();

                // 1. Generar fecha de registro aleatoria (ej. dentro de los últimos 30 días)
                LocalDateTime randomDate = LocalDateTime.now()
                        .minusDays(random.nextInt(30)) // Días aleatorios atrás (0 a 29)
                        .minusHours(random.nextInt(24)) // Horas aleatorias
                        .minusMinutes(random.nextInt(60)) // Minutos aleatorios
                        .minusSeconds(random.nextInt(60)); // Segundos aleatorios
                asistencia.setFechaRegistroAsistencia(randomDate);

                // 2. Generar IDs de usuario, curso, docente, planAcademico aleatorios
                // Usuarios del 1 al 5 (random.nextInt(5) genera 0-4; +1 para 1-5)
                asistencia.setUsuarioIdUsuario(1L + random.nextInt(5));
                // Cursos del 1 al 10 (random.nextInt(10) genera 0-9; +1 para 1-10)
                asistencia.setCursoIdCurso(1 + random.nextInt(10));
                // Docentes del 4 al 6 (se mantiene el rango de ejemplo, random.nextInt(3) genera 0-2; +4 para 4-6)
                asistencia.setDocenteIdDocente(4 + random.nextInt(3));
                // Planes del 7 al 9 (se mantiene el rango de ejemplo, random.nextInt(3) genera 0-2; +7 para 7-9)
                asistencia.setPlanAcademicoIdPlanAcademico(7 + random.nextInt(3));

                // 3. Seleccionar un estado de asistencia aleatorio
                String randomEstado = ESTADOS[random.nextInt(ESTADOS.length)];
                asistencia.setEstadoAsistencia(randomEstado);

                asistenciaRepository.save(asistencia);
            }

            System.out.println("30 datos de asistencias aleatorios insertados correctamente.");
        } else {
            System.out.println("Las asistencias ya existen, no se insertaron datos.");
        }
    }
}