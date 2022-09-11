package ru.kata.spring.boot_security.demo.dao;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT c FROM User c", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(int id, User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(get(id));
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) {
        User user = entityManager.find(User.class, username);
        if (user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User u JOIN fetch u.roles where u.username = :name", User.class)
                .setParameter("name", username)
                .getSingleResult();
    }
}
