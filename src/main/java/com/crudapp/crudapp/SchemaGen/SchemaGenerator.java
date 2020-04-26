package com.crudapp.crudapp.SchemaGen;

import javax.persistence.Persistence;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.crudapp.crudapp.DatabaseConfig")
public class SchemaGenerator {

	public static void main(String[] args) {
		Persistence.generateSchema("emf", null);
	}
}
