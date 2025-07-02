package com.academia.acadcargaacademica.Util;

import com.academia.acadcargaacademica.Entidad.Planacademico;
import com.academia.acadcargaacademica.Repositorio.PlanacademicoRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PlanAcademicoSeeder implements CommandLineRunner {

    private final PlanacademicoRepo planacademicoRepository;
    private final Random random = new Random();

    private static final String[] NOMBRES_PLANES = {
            "Plan 2020", "Plan 2021", "Plan 2022", "Plan 2023",
            "Plan Especial", "Plan Modular", "Plan Intensivo", "Plan Semestral", "Plan Anual", "Plan Técnico"
    };

    private static final String[] DESCRIPCIONES = {
            "Plan académico vigente", "Plan para carreras técnicas", "Plan con enfoque modular",
            "Plan actualizado con competencias", "Plan de reestructuración curricular",
            "Plan para educación continua", "Plan nocturno", "Plan para adultos", "Plan con prácticas", "Plan presencial"
    };

    private static final String[] ESTADOS = {"ACTIVO", "INACTIVO"};

    public PlanAcademicoSeeder(PlanacademicoRepo planacademicoRepository) {
        this.planacademicoRepository = planacademicoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (planacademicoRepository.count() == 0) {
            System.out.println("Insertando planes académicos de prueba...");

            for (int i = 0; i < 10; i++) {
                String nombre = NOMBRES_PLANES[i % NOMBRES_PLANES.length];
                String descripcion = DESCRIPCIONES[random.nextInt(DESCRIPCIONES.length)];
                String estado = ESTADOS[random.nextInt(ESTADOS.length)];

                Planacademico plan = Planacademico.builder()
                        .nombrePlanacademico(nombre)
                        .descripcionPlanacademico(descripcion)
                        .estadoPlanacademico(estado)
                        .build();

                planacademicoRepository.save(plan);
            }

            System.out.println("10 planes académicos insertados correctamente.");
        } else {
            System.out.println("Los planes académicos ya existen. No se insertaron nuevos datos.");
        }
    }
}
