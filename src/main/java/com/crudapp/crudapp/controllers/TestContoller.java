package com.crudapp.crudapp.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TestContoller {
	
	@RequestMapping(value="/sayHello", method=RequestMethod.GET)
	public ResponseEntity<String> sayHello() {
	return ResponseEntity.ok().body("Hello World!");
	
	}

}