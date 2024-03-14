package com.safedose.apimedicamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.safedose.apimedicamentos.repository")
public class MedicamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicamentoApplication.class, args);
	}

}
