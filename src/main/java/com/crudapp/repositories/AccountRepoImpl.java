// package com.crudapp.repositories;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import javax.persistence.EntityManager;
// import javax.persistence.NoResultException;
// import javax.persistence.PersistenceContext;

// import com.crudapp.model.Account;

// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;

// public class AccountRepoImpl implements AccountRepo {

// @PersistenceContext
// private EntityManager em;

// @Override
// public List<Account> findAll() {
// return em.createQuery("select a from account a").getResultList();
// }

// @Override
// public List<Account> findAllById(final Iterable<Long> ids) {
// final List<Account> result = new ArrayList();
// for (final Long i : ids) {
// final Account account = (Account) em.createQuery("select * from account where
// accountID=:id")
// .setParameter("id", i).getSingleResult();
// if (account != null) {
// result.add(account);
// } else {
// continue;
// }
// }
// return result;
// }

// @Override
// public <S extends Account> List<S> saveAll(Iterable<S> entities) {
// List<S> a = new ArrayList();
// for (S entity : entities) {
// if (entity.getAccountID() == null) {
// em.persist(entity);
// System.out.println(entity.getFirst_name() + " Has been added");
// } else if (entity.getAccountID() != null) {
// em.merge(entity);
// System.out.println(entity.getAccountID() + " Has been updated");
// }
// a.add(entity);
// }
// return a;
// }

// @Override
// public <S extends Account> S save(final S entity) {
// if (entity.getAccountID() == null) {
// em.persist(entity);
// }
// if (entity.getAccountID() != null) {
// em.merge(entity);
// }
// return entity;
// }

// @Override
// public Account getOne(final Long id) {
// return (Account) em.createQuery("select * from account a where a.accountID =
// :id").setParameter("id", id)
// .getSingleResult();
// }

// @Override
// public long count() {
// return (long) em.createQuery("select count(*) from
// account").getSingleResult();
// }

// @Override
// public void deleteById(final Long id) {
// em.remove(getOne(id));
// System.out.println("Account has been successfuly deleted");
// }

// @Override
// public List<Account> findAll(final Sort sort) {
// // TODO Auto-generated method
// return null;
// }

// @Override
// public Page<Account> findAll(final Pageable pageable) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public Optional<Account> findById(final Long id) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public boolean existsById(final Long id) {
// // TODO Auto-generated method stub
// return false;
// }

// @Override
// public void deleteAll(final Iterable<? extends Account> entities) {
// // TODO Auto-generated method stub

// }

// @Override
// public void deleteAll() {
// // TODO Auto-generated method stub

// }

// @Override
// public <S extends Account> List<S> findAll(final Example<S> example) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public <S extends Account> List<S> findAll(final Example<S> example, final
// Sort sort) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public <S extends Account> Optional<S> findOne(final Example<S> example) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public <S extends Account> Page<S> findAll(final Example<S> example, final
// Pageable pageable) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public void flush() {
// // TODO Auto-generated method stub

// }

// @Override
// public <S extends Account> S saveAndFlush(final S entity) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public <S extends Account> long count(final Example<S> example) {
// // TODO Auto-generated method stub
// return 0;
// }

// @Override
// public void deleteInBatch(final Iterable<Account> entities) {
// // TODO Auto-generated method stub

// }

// @Override
// public void deleteAllInBatch() {
// // TODO Auto-generated method stub

// }

// @Override
// public <S extends Account> boolean exists(final Example<S> example) {
// // TODO Auto-generated method stub
// return false;
// }

// @Override
// public void delete(final Account entity) {
// // TODO Auto-generated method stub

// }

// @Override
// public Optional<Account> findByUsername(String username) {
// try {
// return Optional.of(em.createQuery("select * from account where username=:un",
// Account.class)//
// .setParameter("un", username)//
// .getSingleResult());
// } catch (NoResultException nre) {
// return Optional.empty();
// }
// }

// }