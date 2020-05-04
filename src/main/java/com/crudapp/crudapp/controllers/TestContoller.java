package com.crudapp.crudapp.controllers;

import com.crudapp.crudapp.repo.CrudAppJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJpaRepositories
public class TestContoller {

	@Autowired
	CrudAppJpaRepository repo;

	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public String sayHello() {
		return "Hello World";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String requestMethodName(@RequestParam String param) {
		return "Hello My Name is Nikita";
	}

}