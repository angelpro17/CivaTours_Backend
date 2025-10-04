package com.civa.challenge.civatours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class CivaToursApplication {

    public static void main(String[] args) {
        SpringApplication.run(CivaToursApplication.class, args);
    }
}
