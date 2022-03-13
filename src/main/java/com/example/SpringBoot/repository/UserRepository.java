package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    Optional<User> find(Long id);

    User find(String email);

    void save(User entity);

    void delete(Long id);

    User findByName(String name);

    void update(User user);
}
