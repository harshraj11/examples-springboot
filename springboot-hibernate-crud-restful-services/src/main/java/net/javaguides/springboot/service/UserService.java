package net.javaguides.springboot.service;

import net.javaguides.springboot.dao.UserDAO;
import net.javaguides.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }
}