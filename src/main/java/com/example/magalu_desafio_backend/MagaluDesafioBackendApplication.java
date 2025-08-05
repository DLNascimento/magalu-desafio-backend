package com.example.magalu_desafio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MagaluDesafioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagaluDesafioBackendApplication.class, args);
	}

}
