package com.example.PP_311_SpringBoot.controller;

import com.example.PP_311_SpringBoot.model.UserData;
import com.example.PP_311_SpringBoot.model.Role;
import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.RoleService;
import com.example.PP_311_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        return "admin";
    }


    @GetMapping("/admin/get/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.getById(userId));
        return "admin";
    }

    @GetMapping(value = "admin/new")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roleList", null);
        model.addAttribute("userData", new UserData());
        return "form";
    }

    @GetMapping("/admin/edit")
    public String editUserForm(@RequestParam(value = "id", required = true) long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleService.getAll());
        model.addAttribute("userData", new UserData(
                user.getName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail()));
        System.out.println(user.getEmail());
        return "form";
    }

    @PostMapping(value = "/admin/new")
    public String saveUser(@ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ROLE_USER"));
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam(value = "id", required = true, defaultValue = "") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}