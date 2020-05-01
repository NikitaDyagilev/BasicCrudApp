package com.crudapp.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.crudapp.crudapp.model")
public class CrudappApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CrudappApplication.class, args);
	}

}
