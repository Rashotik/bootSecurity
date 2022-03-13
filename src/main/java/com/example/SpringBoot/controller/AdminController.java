package com.example.SpringBoot.controller;

import com.example.SpringBoot.model.UserData;
import com.example.SpringBoot.model.Role;
import com.example.SpringBoot.model.User;
import com.example.SpringBoot.repository.RoleRepository;
import com.example.SpringBoot.service.RoleService;
import com.example.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public String userList(Model model, Principal principal) {
        User user = userService.getByName(principal.getName());
        model.addAttribute("allUsers", userService.getAll());
        model.addAttribute("user", user);
        model.addAttribute("roleList", null);
        model.addAttribute("userData", new UserData(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail()));
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
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail()));
        System.out.println(user.getEmail()+"       asdawdasdwawdawdawawc");
        return "form";
    }

    @PostMapping(value = "/admin/new")
    public String saveUser(@ModelAttribute("userData") UserData userData) {
        User user = new User();
        user.setName(userData.getName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());

        Set<Role> roles = new HashSet<>();
        if(userData.getRoles() != null){
            for (String role : userData.getRoles()) {
                roles.add(new Role(roleService.getByName(role).get(0).getId(), role));
            }
        }else{
            roles.add(new Role(1L,"ROLE_USER"));
        }

        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

}