package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleDao {
    Role getRoleByName(String name);

    Set<Role> getAllRoles();

    Set<Role> getRolesByName(Set<Role> roles);

    void addRole(Role role);
}
