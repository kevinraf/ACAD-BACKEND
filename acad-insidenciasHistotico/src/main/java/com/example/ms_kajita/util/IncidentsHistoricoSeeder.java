package com.example.ms_kajita.util;

import com.example.ms_kajita.entity.InsidenciaHistorico;
import com.example.ms_kajita.repository.InsidenciaHistoricoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random; // Necesitamos esto para números aleatorios

@Component
public class IncidentsHistoricoSeeder implements CommandLineRunner {

    private final InsidenciaHistoricoRepository insidenciaHistoricoRepository;
    private final Random random = new Random(); // Instancia de Random

    // Arrays de datos de ejemplo para generar incidencias más variadas
    private static final String[] TITULOS = {
            "Problema de Acceso a Plataforma",
            "Error en Calificación de Examen",
            "Duda sobre Horario de Clase",
            "Inconveniente con Material del Curso",
            "Soporte Técnico Requerido",
            "Queja sobre Docente",
            "Solicitud de Certificado",
            "Problema de Conectividad en Aula",
            "Reclamo por Asistencia",
            "Consulta de Créditos Académicos"
    };

    private static final String[] DESCRIPCIONES = {
            "El estudiante reporta que no puede iniciar sesión en el campus virtual.",
            "Hay un error en la nota final del examen de cálculo para el alumno.",
            "Se presenta una confusión con el nuevo horario de clases de química.",
            "El material de lectura del módulo 3 no carga correctamente.",
            "Se necesita asistencia para configurar el IDE para la clase de programación.",
            "Un grupo de estudiantes expresa descontento con la metodología del profesor X.",
            "El alumno requiere una constancia de estudios para trámites externos.",
            "La conexión a internet en el laboratorio de cómputo es intermitente.",
            "El estudiante afirma que su asistencia fue marcada incorrectamente el día 15.",
            "Se solicita información sobre el avance de créditos para graduación."
    };

    public IncidentsHistoricoSeeder(InsidenciaHistoricoRepository insidenciaHistoricoRepository) {
        this.insidenciaHistoricoRepository = insidenciaHistoricoRepository;
    }

    @Override
    public void run(String... args) {
        if (insidenciaHistoricoRepository.count() == 0) {
            System.out.println("Insertando 10 datos de incidencias históricas aleatorias...");

            for (int i = 0; i < 10; i++) { // Bucle para insertar 10 registros
                InsidenciaHistorico incidencia = new InsidenciaHistorico();

                // Generar fecha de registro aleatoria (ej. dentro de los últimos 60 días)
                LocalDateTime randomDate = LocalDateTime.now()
                        .minusDays(random.nextInt(60)) // Días aleatorios atrás (0 a 59)
                        .minusHours(random.nextInt(24)) // Horas aleatorias
                        .minusMinutes(random.nextInt(60)) // Minutos aleatorios
                        .minusSeconds(random.nextInt(60)); // Segundos aleatorios
                incidencia.setFechaRegistro(randomDate);

                // Seleccionar título y descripción aleatorios
                incidencia.setTitulo(TITULOS[random.nextInt(TITULOS.length)]);
                incidencia.setDescripccion(DESCRIPCIONES[random.nextInt(DESCRIPCIONES.length)]);

                // Generar IDs aleatorios para usuario, docente y plan académico
                // idUsuario: del 1 al 5
                incidencia.setUsuarioIdUsuario(1L + random.nextInt(5)); // Genera 1, 2, 3, 4, 5
                // idDocente: del 201 al 205 (ejemplo)
                incidencia.setDocenteIdDocente(201 + random.nextInt(5));
                // idPlanAcademico: del 301 al 305 (ejemplo)
                incidencia.setPlanAcademicoIdPlanAcademico(301 + random.nextInt(5));

                insidenciaHistoricoRepository.save(incidencia);
            }

            System.out.println("10 datos de incidencias históricas aleatorias insertados correctamente.");
        } else {
            System.out.println("Las incidencias históricas ya existen, no se insertaron datos.");
        }
    }
}
