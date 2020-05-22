package com.crudapp.services;

import java.util.List;
import java.util.Optional;

import com.crudapp.model.Account;
import com.crudapp.repositories.AccountRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

	@Autowired
	public AccountRepo accountRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("username: " + username + " not found"));
	}

	public Account createAccount(Account account) throws IllegalArgumentException {
		return accountRepo.save(account);
	}

	public List<Account> readAccount(Account probe) throws IncorrectResultSizeDataAccessException {
		return accountRepo.findAll(Example.of(probe));
	}

	public Optional<Account> readAccount(Long id) throws IllegalArgumentException {
		return accountRepo.findById(id);
	}

	public Account updateAccount(Account probe) {
		return createAccount(probe);
	}

	public Boolean deleteAccount(Long id) {
		accountRepo.deleteById(id);
		return accountRepo.existsById(id);
	}

}