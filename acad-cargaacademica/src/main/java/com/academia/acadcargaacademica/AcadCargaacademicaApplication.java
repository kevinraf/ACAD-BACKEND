package com.academia.acadcargaacademica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AcadCargaacademicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadCargaacademicaApplication.class, args);
    }

}
