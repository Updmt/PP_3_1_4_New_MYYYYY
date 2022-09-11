package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> getAllRoles();

    Role getRoleByName(String name);

    Set<Role> getRolesByName(Set<Role> roles);

    void addRole(Role role);
}
