package com.example.PP_311_SpringBoot.service;

import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userDao;

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(Long id) {
        return userDao.find(id).get();
    }

    @Override
    public User getByName(String name) {
        return userDao.findByName(name);
    }

    public User getByEmail(String email) {
        return userDao.find(email);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void update(User updatedUser) {
        userDao.update(updatedUser);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDao.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        return getByEmail(s);
    }
}
