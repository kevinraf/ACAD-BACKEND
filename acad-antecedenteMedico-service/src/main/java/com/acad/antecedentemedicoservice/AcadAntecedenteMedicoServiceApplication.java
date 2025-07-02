package com.acad.antecedentemedicoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AcadAntecedenteMedicoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadAntecedenteMedicoServiceApplication.class, args);
    }

}
