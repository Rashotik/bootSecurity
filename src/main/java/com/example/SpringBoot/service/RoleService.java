package com.example.SpringBoot.service;

import com.example.SpringBoot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getById(Long id);
    List<Role> getByName(String name);

    void save(Role role);
}
