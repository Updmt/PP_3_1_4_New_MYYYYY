package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;


@RestController
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /*@GetMapping("/admin")
    public String showAllUsers(Principal principal, Model model){
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", userService.createUser());
        model.addAttribute("listRoles",roleService.getAllRoles());
        return "admin";
    }

    @PostMapping("/admin/new")
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "user";
    }

    @PutMapping("/admin/updateUser/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }*/

    @GetMapping("/admin")
    public List<User> showAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }

    @GetMapping("/admin/{id}")
    public User getUser(@PathVariable("id") int id) {
        User user = userService.get(id);
        return user;
    }

    @PostMapping("/admin")
    public User addNewUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    @PutMapping("/admin")
    public User updateUser(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "User with id = " + id + " was deleted";
    }
}
