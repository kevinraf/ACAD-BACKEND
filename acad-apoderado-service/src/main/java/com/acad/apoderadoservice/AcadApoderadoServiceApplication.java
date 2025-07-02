package com.acad.apoderadoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AcadApoderadoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadApoderadoServiceApplication.class, args);
    }

}
