package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;


@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/start")
    public String start(){
        return "startPage";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/users/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", userService.createUser());
        model.addAttribute("role", roleService.getAllRoles());
        return "user-info";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("newUser") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println("Binderr");
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    /*@PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user){
        userService.createUser(user);
        return "redirect:/users";
    }*/

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "show";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Set<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", userService.get(id));
        model.addAttribute("role", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
