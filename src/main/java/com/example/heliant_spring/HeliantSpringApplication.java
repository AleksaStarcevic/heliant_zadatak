package com.example.heliant_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class HeliantSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeliantSpringApplication.class, args);
    }

}
