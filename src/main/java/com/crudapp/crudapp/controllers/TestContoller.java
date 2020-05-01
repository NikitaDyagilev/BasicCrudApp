package com.crudapp.crudapp.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.crudapp.crudapp.repo.CrudAppJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@EnableJpaRepositories
public class TestContoller {


	@Autowired
	CrudAppJpaRepository repo;

	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok().body("Hello World!");
	}

}