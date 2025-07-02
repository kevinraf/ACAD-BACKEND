package com.acad.requisitoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AcadRequisitoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadRequisitoServiceApplication.class, args);
    }

}
