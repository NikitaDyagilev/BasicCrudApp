package com.crudapp.crudapp.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
//All you need to enable Jpa Repos is this annotation: 
// It will encapsulate entityManagerFactory Class and inject them as needed
@EnableJpaRepositories 
public class TestContoller {

	@RequestMapping(value="/sayHello", method=RequestMethod.GET)
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok().body("Hello World!");
	}

	
	
	
}