package com.academia.acadcargaacademica.Util;

import com.academia.acadcargaacademica.Entidad.Docente;
import com.academia.acadcargaacademica.Repositorio.DocenteRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DocenteSeeder implements CommandLineRunner {

    private final DocenteRepositorio docenteRepository;
    private final Random random = new Random();

    private static final String[] NOMBRES = {"Juan", "María", "Pedro", "Lucía", "Carlos", "Ana", "Luis", "Paola", "José", "Elena"};
    private static final String[] APELLIDOS = {"Pérez", "García", "Ramírez", "Torres", "Flores", "Rodríguez", "Martínez", "Sánchez", "Mendoza", "Vásquez"};
    private static final String[] ESTADOS = {"ACTIVO", "INACTIVO"};
    private static final String[] CARGOS = {"Docente Principal", "Docente Auxiliar", "Jefe de Área", "Coordinador Académico"};

    public DocenteSeeder(DocenteRepositorio docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (docenteRepository.count() == 0) {
            System.out.println("Insertando docentes de prueba...");

            for (int i = 0; i < 10; i++) {
                String nombre = NOMBRES[i % NOMBRES.length];
                String apellidoPaterno = APELLIDOS[random.nextInt(APELLIDOS.length)];
                String apellidoMaterno = APELLIDOS[random.nextInt(APELLIDOS.length)];
                String correo = nombre.toLowerCase() + "." + apellidoPaterno.toLowerCase() + "@academia.edu";
                String telefono = "9" + (10000000 + random.nextInt(89999999)); // Número tipo 9XXXXXXXX
                String estado = ESTADOS[random.nextInt(ESTADOS.length)];
                String cargo = CARGOS[random.nextInt(CARGOS.length)];
                String dni = String.valueOf(10000000 + random.nextInt(89999999)); // 8 dígitos
                String antiguedad = (1 + random.nextInt(20)) + " años"; // entre 1 y 20 años

                Docente docente = Docente.builder()
                        .nombreDocente(nombre)
                        .apellidoPaternoDocente(apellidoPaterno)
                        .apellidoMaternoDocente(apellidoMaterno)
                        .correoDocente(correo)
                        .telefonoDocente(telefono)
                        .estadoDocente(estado)
                        .cargoDocente(cargo)
                        .dniDocente(dni)
                        .antiguedadDocente(antiguedad)
                        .build();

                docenteRepository.save(docente);
            }

            System.out.println("10 docentes insertados correctamente.");
        } else {
            System.out.println("Los docentes ya existen. No se insertaron nuevos datos.");
        }
    }
}
