package com.yqq.acadsede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AcadSedeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcadSedeApplication.class, args);
    }

}
