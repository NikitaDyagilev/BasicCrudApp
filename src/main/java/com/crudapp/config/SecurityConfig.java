package com.crudapp.config;

import com.crudapp.security.JwtConfigurer;
import com.crudapp.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// TODO: add endponts/methods for which you DONT want jwt required below
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http//
				.cors().and().httpBasic().disable()//
				.csrf().disable()//
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
				.and().authorizeRequests()//
				.mvcMatchers(HttpMethod.GET, //
						"/endpoint")//
				.permitAll()//
				.anyRequest().authenticated()//
				.and().apply(new JwtConfigurer(jwtTokenProvider));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return this.userDetailsService;
	}

	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		// firewall.setAllowBackSlash(true);
		// firewall.setAllowUrlEncodedSlash(true);
		return firewall;
	}

}
