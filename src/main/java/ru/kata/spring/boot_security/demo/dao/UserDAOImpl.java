package ru.kata.spring.boot_security.demo.dao;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserDAOImpl(EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class)
                .getResultList();
    }

    @Override
    public void createUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User updatedUser) {
        //updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        entityManager.merge(updatedUser);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(get(id));
    }


    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User AS u JOIN FETCH u.roles WHERE u.username= :username", User.class)
                .setMaxResults(1)
                .setParameter("username", username)
                .getSingleResult();
    }
}
