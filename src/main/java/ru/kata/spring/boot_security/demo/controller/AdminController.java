package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class AdminController {


    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.getUserByUsername(user.getUsername()));
        model.addAttribute("roles", userService.getUserByUsername(user.getUsername()).getRoles());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

}
