package com.example.PP_311_SpringBoot.controller;

import com.example.PP_311_SpringBoot.model.UserData;
import com.example.PP_311_SpringBoot.model.Role;
import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.RoleService;
import com.example.PP_311_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class EditController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/admin/edit")
    public String updateUser(@ModelAttribute("userData") UserData userData) {
        System.out.println(userData.getEmail()+"aa a a a a a a a a a a a  a a a a a");
        User user = userService.getById(userData.getId());
        Set<Role> roles = new HashSet<>();
        for (String role:userData.getRoles()
        ) {
            roles.add(roleService.getByName(role).get(0));
        }
        user.setName(userData.getName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }
}
