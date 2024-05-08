package com.sportyshoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("com.sportyshoes.model")  // Scan JPA entities
@EnableJpaRepositories("com.sportyshoes.repository")  // Scan JPA repositories

public class SportyShoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportyShoesApplication.class, args);
    }
}


