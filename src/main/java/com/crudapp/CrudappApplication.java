package com.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CrudappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudappApplication.class, args);
	}

}
