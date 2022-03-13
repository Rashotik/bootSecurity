package com.example.SpringBoot.controller;

import com.example.SpringBoot.model.User;
import com.example.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {
    @Autowired
    private UserService userService;

    @PostMapping("/admin/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        System.out.println(user.getId());
        userService.deleteById(user.getId());
        return "redirect:/admin";
    }
}
