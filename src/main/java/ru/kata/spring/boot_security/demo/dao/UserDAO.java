package ru.kata.spring.boot_security.demo.dao;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;


public interface UserDAO{

    List<User> getAllUsers();

    void createUser(User user);

    User get(int id);

    void update(int id, User updatedUser);

    void delete(int id);

}
