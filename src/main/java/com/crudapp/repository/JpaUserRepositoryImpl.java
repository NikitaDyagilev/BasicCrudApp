package com.crudapp.repository;

import com.crudapp.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Transactional
@Repository("jpaUserRepositoryImpl")
public class JpaUserRepositoryImpl implements JpaUserRepository {

    @PersistenceContext
    private EntityManager em;


    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll() {
        return em
                .createQuery("select u from User u").getResultList();
    }

    @Override
    public Optional<User> findById(Long aLong) {

        User account = (User) em
                .createQuery("select u from User u where u.id = :id")
                .setParameter("id", aLong).getSingleResult();
        Optional<User> optionalUser = Optional.ofNullable(account);
        return optionalUser;
    }

    @Override
    public <S extends User> S save(S entity) {
        boolean exists;
        try {
            exists = exists(Example.of(entity));
        } catch (NoResultException e) {
            exists = false;
        }
        if (exists) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        return entity;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        List<S> accounts = new ArrayList<>();

        for (S acc : entities) {

            if (exists((Example.of(acc)))) {
                em.persist(acc);
            } else {
                em.merge(acc);
            }
            accounts.add(acc);
        }
        return accounts;
    }

    @Override
    public void delete(User entity) {
        em.remove(entity);
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        Long id = example.getProbe().getId();
        return em.createQuery("from User u where u.id=:id")
                .setParameter("id", id).setMaxResults(1).getSingleResult() != null;
    }

    @Override
    public long count() {
        return (long) em
                .createQuery("select count(*) from User")
                .getSingleResult();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> user = Optional.of( (User) em.createQuery("select u from User u where u.username=:username")
                .setParameter("username", username)
                .getSingleResult());
        if(user.isEmpty()){
            user = Optional.of( (User) em.createQuery("select u from User u where u.username=:username")
                    .setParameter("username", username)
                    .getResultList().get(0));
        }
        return user;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
    }


    @Override
    public void deleteById(Long aLong) {

    }


    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

}
