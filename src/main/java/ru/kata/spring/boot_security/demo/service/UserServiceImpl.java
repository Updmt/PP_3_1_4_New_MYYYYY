package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDAO userDAO;
    private final RoleDao roleDao;



    public UserServiceImpl(UserDAO userDAO, RoleDao roleDao) {
        this.userDAO = userDAO;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        user.setRoles(roleDao.getRolesByName(user.getRoles()));
        userDAO.createUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    @Transactional
    public void update(User updatedUser) {
        updatedUser.setRoles(roleDao.getRolesByName(updatedUser.getRoles()));
        userDAO.update(updatedUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.loadUserByUsername(username);
    }

    @Override
    @Transactional
    public User createUser() {
        User user = new User();
        Role roleUser = roleDao.getRoleByName("ROLE_USER");
        user.addRole(roleUser);
        return user;
    }

}
