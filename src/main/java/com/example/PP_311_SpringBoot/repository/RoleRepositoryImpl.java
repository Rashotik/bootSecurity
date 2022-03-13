package com.example.PP_311_SpringBoot.repository;

import org.springframework.stereotype.Repository;
import com.example.PP_311_SpringBoot.model.Role;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    public Role findRoleByAuthority(String authority) throws NoSuchElementException {
        return findAll().stream()
                .filter(r -> authority.equals(r.getAuthority()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Role %s not found", authority)));
    }
    @Transactional
    public void save(Role role){
        entityManager.persist(role);
        entityManager.merge(role);
    }
    public Role getById(Long id) {
        return entityManager.createQuery("select r from Role r WHERE r.id =:id", Role.class)
                .setParameter("id", id).getSingleResult();
    }
    public List<Role> getByName(String name) {
        return entityManager.createQuery("select r from Role r WHERE r.name =:name", Role.class)
                .setParameter("name", name).getResultList();
    }
}
