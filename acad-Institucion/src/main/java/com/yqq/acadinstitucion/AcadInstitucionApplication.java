package com.yqq.acadinstitucion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AcadInstitucionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadInstitucionApplication.class, args);
    }

}
