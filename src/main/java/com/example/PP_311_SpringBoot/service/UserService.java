package com.example.PP_311_SpringBoot.service;

import com.example.PP_311_SpringBoot.model.User;
import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    User getByName(String name);
    User getByEmail(String email);
    void save(User user);
    void update(User updatedUser);
    void deleteById(Long id);
}
