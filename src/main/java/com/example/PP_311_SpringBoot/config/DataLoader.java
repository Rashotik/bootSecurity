package com.example.PP_311_SpringBoot.config;

import com.example.PP_311_SpringBoot.model.Role;
import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.repository.RoleRepository;
import com.example.PP_311_SpringBoot.repository.UserRepository;
import com.example.PP_311_SpringBoot.service.RoleService;
import com.example.PP_311_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        LoadUsers();
    }

    private void LoadUsers() {
        User user = new User();
        user.setEmail("user@asd.dawd");
        user.setPassword("user");
        user.setName("username");
        user.setLastName("userlastname");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);

        user = new User();
        user.setEmail("root@root.asd");
        user.setPassword("root");
        user.setName("rootname");
        user.setLastName("rootlastname");

        roles = new HashSet<>();
        roles.add(new Role(1L, "ROLE_USER"));
        roles.add(new Role(2L, "ROLE_ADMIN"));
        user.setRoles(roles);
        userRepository.save(user);

        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_ADMIN"));
    }
}