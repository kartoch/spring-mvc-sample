package fr.plil.sio.web.mvc;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    private void checkUsername(String username, Errors errors) {

    }

    private void checkPassword(String password, Errors errors) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");

        if (!hasUppercase) {
            errors.rejectValue("password", "validator.user.form.must.contains.uppercase");
        }
        if (!hasLowercase) {
            errors.rejectValue("password", "validator.user.form.must.contains.lowercase");
        }
        if (!hasSpecial) {
            errors.rejectValue("password", "validator.user.form.must.contains.special.char");
        }
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm user = (UserForm) target;
        checkUsername(user.getUsername(), errors);
        checkPassword(user.getPassword(), errors);
    }
}