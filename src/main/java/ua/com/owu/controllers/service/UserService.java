package ua.com.owu.controllers.service;

import ua.com.owu.controllers.models.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> findAll();

    User findById(int id);
}
