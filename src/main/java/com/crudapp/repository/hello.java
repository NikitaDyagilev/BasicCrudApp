package com.crudapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;

public class hello {

    @Autowired
    @Qualifier("entityManagerFactory")
    private final EntityManagerFactory emf;

    public hello(EntityManagerFactory emf) {
        this.emf = emf;
    }

}
