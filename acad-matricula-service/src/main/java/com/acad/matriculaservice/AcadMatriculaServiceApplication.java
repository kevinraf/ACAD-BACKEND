package com.acad.matriculaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AcadMatriculaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadMatriculaServiceApplication.class, args);
    }

}
