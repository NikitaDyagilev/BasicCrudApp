package com.crudapp.crudapp.SchemaGen;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.crudapp.crudapp.DatabaseConfig")
public class SchemaGenerator{

    public static void main(String[] args){
        Persistence.generateSchema("emf", null);
    }
}

