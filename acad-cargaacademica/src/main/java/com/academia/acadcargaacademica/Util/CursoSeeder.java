package com.academia.acadcargaacademica.Util;

import com.academia.acadcargaacademica.Entidad.Curso;
import com.academia.acadcargaacademica.Repositorio.CursoRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CursoSeeder implements CommandLineRunner {

    private final CursoRepositorio cursoRepository;
    private final Random random = new Random();

    // Ejemplos de nombres y descripciones de cursos
    private static final String[] NOMBRES_CURSOS = {
            "Matemáticas", "Física", "Lenguaje", "Historia", "Biología",
            "Química", "Computación", "Inglés", "Educación Física", "Arte"
    };

    private static final String[] DESCRIPCIONES = {
            "Curso introductorio", "Curso avanzado", "Curso intermedio",
            "Enfoque práctico", "Teoría y práctica", "Curso obligatorio",
            "Curso optativo", "Laboratorio incluido", "Con evaluación continua", "Curso semestral"
    };

    public CursoSeeder(CursoRepositorio cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (cursoRepository.count() == 0) {
            System.out.println("Insertando cursos de prueba...");

            for (int i = 0; i < 10; i++) {
                String nombre = NOMBRES_CURSOS[i % NOMBRES_CURSOS.length];
                String descripcion = DESCRIPCIONES[random.nextInt(DESCRIPCIONES.length)];
                String horasSemanales = (2 + random.nextInt(4)) + " horas/semana"; // Entre 2 y 5 horas

                Curso curso = Curso.builder()
                        .nombreCurso(nombre)
                        .descripcionCurso(descripcion)
                        .horasSemanalesCurso(horasSemanales)
                        .build();

                cursoRepository.save(curso);
            }

            System.out.println("10 cursos insertados correctamente.");
        } else {
            System.out.println("Los cursos ya existen. No se insertaron nuevos datos.");
        }
    }
}
