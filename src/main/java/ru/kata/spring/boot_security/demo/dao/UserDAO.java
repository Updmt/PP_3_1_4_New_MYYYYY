package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO{

    List<User> getAllUsers();

    void createUser(User user);

    User get(int id);

    void update(User updatedUser);

    void delete(int id);

    User getUserByUsername(String username);

}
