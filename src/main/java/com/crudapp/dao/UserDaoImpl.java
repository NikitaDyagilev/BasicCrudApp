package com.crudapp.dao;

import com.crudapp.model.User;
import com.crudapp.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("accountDaoImpl")
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("jpaUserRepositoryImpl")
    private JpaUserRepository jpaRepo;

    public void setJpaRepo(JpaUserRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }


    @Override
    public List<User> getAll() {
        return jpaRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> Oaccount = jpaRepo.findById(id);
        if(Oaccount.isPresent() == true) {
            User account = Oaccount.get();
            return account;
        } else {
            System.out.println("    >>>Could not find User by that ID");
            return null;
        }
    }

    @Override
    public void delete(User account) {
        jpaRepo.delete(account);

    }

    @Override
    public void save(User account){
        jpaRepo.save(account);
    }

    @Override
    public long count() {
        return jpaRepo.count();
    }
}
