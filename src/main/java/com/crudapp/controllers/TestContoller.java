package com.crudapp.controllers;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJpaRepositories
public class TestContoller {

	@RequestMapping(value = "/unsecured", method = RequestMethod.GET)
	public String unsecured() {
		return "Everybody can see this";
	}

	@RequestMapping(value = "/secured", method = RequestMethod.GET)
	public String secured() {
		return "This is a sooper speshl secrit";
	}

}