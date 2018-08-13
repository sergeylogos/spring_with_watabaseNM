package ua.com.owu.controllers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.owu.controllers.dao.UserDAO;
import ua.com.owu.controllers.models.User;

import java.util.List;

@Service
public class UserService2 implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public void save(User user) {
        if (user != null) {
            userDAO.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        Sort orders = Sort.by(Sort.Direction.DESC);
        return userDAO.findAll(orders);
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
