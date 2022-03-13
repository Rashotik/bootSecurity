package com.example.PP_311_SpringBoot.repository;

import com.example.PP_311_SpringBoot.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleRepository {
    List<Role> findAll();

    Role findRoleByAuthority(String authority) throws NoSuchElementException;

    void save(Role role);

    Role getById(Long id);

    List<Role> getByName(String name);
}
