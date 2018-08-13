package ua.com.owu.controllers.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.owu.controllers.models.User;
import ua.com.owu.controllers.service.UserService;

@Component
public class UserValidator implements Validator {


    @Autowired
    @Qualifier("userService2")
    private UserService userService;

    @Override
    public boolean supports(Class<?> outerUser) {
        return User.class.equals(outerUser);
    }

    @Override
    public void validate(Object user, Errors errors) {
        User u = (User) user;
        if (u.getUsername().isEmpty()) {
            errors.rejectValue("username", "name.empty", "default empty");
        }

        if (userService.findAll().stream().anyMatch(user1 -> user1.getUsername().equals(((User) user).getUsername()))) {

            errors.rejectValue("username", "name.exist", "name already exist");
        }



    }
}
