package com.example.PP_311_SpringBoot.service;

import com.example.PP_311_SpringBoot.model.Role;
import com.example.PP_311_SpringBoot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleDao;

    @Override
    public List<Role> getAll() {
        return roleDao.findAll();
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public List<Role> getByName(String name) {
        return roleDao.getByName(name);
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

}
