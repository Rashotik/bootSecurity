package com.example.PP_311_SpringBoot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.example.PP_311_SpringBoot.model.User;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    @Override
    public User find(String email) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
    @Override
    public User findByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.name = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Transactional
    @Override
    public void save(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        try {
            entityManager.persist(entity);
            entityManager.flush();
        }catch (Exception e){}
    }
    public void update(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User entity = find(id).get();
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
