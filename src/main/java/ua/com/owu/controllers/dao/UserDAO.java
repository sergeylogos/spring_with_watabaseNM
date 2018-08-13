package ua.com.owu.controllers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.controllers.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
}
