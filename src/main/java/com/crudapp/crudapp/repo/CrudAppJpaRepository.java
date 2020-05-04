package com.crudapp.crudapp.repo;

import com.crudapp.crudapp.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudAppJpaRepository extends JpaRepository<Account, Long> {

}