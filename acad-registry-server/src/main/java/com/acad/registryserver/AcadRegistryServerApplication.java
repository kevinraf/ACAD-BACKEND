package com.acad.registryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AcadRegistryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadRegistryServerApplication.class, args);
    }

}
