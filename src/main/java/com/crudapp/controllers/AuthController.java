package com.crudapp.controllers;

import java.util.Map;

import com.crudapp.model.Account;
import com.crudapp.security.JwtTokenProvider;
import com.crudapp.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public class AuthController {

	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtTokenProvider jwtProvider;
	@Autowired
	AccountService accountService;
	@Autowired
	PasswordEncoder encoder;

	@PostMapping(value = "/signin")
	public ResponseEntity<Object> signin(MultipartHttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		if (!paramMap.containsKey("u")//
				|| !paramMap.containsKey("p"))
			return ResponseEntity.badRequest().body("Request is missing required parameters");
		String username = request.getParameter("u");
		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, request.getParameter("p")));
		return ResponseEntity.ok().body(jwtProvider.createToken(username, (Account) auth.getPrincipal()));
	}

	@PostMapping(value = "/signup")
	public ResponseEntity<Object> signup(MultipartHttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		if (!paramMap.containsKey("fn")//
				|| !paramMap.containsKey("ln")//
				|| !paramMap.containsKey("u")//
				|| !paramMap.containsKey("p"))
			return ResponseEntity.badRequest().body("Request is missing required parameters");
		try {
			Account account = Account.builder()//
					.first_name(request.getParameter("fn"))//
					.last_name(request.getParameter("ln"))//
					.username(request.getParameter("u"))//
					.password(encoder.encode(request.getParameter("p")))//
					.build();
			accountService.createAccount(account);
			return ResponseEntity.ok("Sign up successful");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Could not fulfill request for unknown reason");
		}
	}
}