package com.crudapp.repositories;

import java.util.Optional;

import com.crudapp.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
	Optional<Account> findByUsername(String username);
}