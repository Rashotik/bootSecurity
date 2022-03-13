package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleRepository {
    List<Role> findAll();

    Role findRoleByAuthority(String authority) throws NoSuchElementException;

    void save(Role role);

    Role getById(Long id);

    List<Role> getByName(String name);
}
