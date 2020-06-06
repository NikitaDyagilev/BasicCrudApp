package com.crudapp.controllers;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
@EnableJpaRepositories
public class TestContoller {

	@RequestMapping(value = "/unsecured", method = RequestMethod.GET)
	public String unsecured() {
		return "index";
	}

	@RequestMapping(value = "/secured", method = RequestMethod.GET)
	public String secured() {
		return "This is a sooper speshl secrit";
	}

}