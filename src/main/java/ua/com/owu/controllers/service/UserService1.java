package ua.com.owu.controllers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.controllers.dao.UserDAO;
import ua.com.owu.controllers.models.User;

import java.util.List;

@Service
public class UserService1 implements UserService {


    @Autowired
    private UserDAO userDAO;

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id).get();
    }
}
