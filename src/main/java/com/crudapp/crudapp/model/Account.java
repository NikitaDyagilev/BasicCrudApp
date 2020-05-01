package com.crudapp.crudapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Table
@Data

public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;
    
    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "password")
    private String password;

    @Column(name="username")
    private String username;

    @Column(name = "bio")
    private String bio;

    


}