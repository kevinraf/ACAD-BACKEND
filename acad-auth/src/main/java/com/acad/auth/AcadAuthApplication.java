package com.acad.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class AcadAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadAuthApplication.class, args);
    }

    @Bean
    @LoadBalanced // Agrega esta anotación
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
