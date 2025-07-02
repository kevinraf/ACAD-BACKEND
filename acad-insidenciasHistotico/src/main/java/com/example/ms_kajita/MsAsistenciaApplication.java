package com.example.ms_kajita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
public class MsAsistenciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAsistenciaApplication.class, args);
    }

    @Bean
    @LoadBalanced // Agrega esta anotación
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
