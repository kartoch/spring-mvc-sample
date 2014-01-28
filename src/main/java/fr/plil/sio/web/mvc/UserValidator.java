package fr.plil.sio.web.mvc;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;

        if (user.getUsername().length() < 3) {
            errors.rejectValue("username", "validator.user.username.minimal.size");
        }

        if (user.getPassword().length() < 3) {
            errors.rejectValue("password", "validator.user.password.minimal.size");
        }
    }

}
