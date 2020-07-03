package com.crudapp.dao;

import com.crudapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAll();
    User findById(Long id);
    void delete(User account);
    void save(User account);
    long count();
}
