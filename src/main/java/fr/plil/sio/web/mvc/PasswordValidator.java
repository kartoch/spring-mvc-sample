package fr.plil.sio.web.mvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm userForm = (UserForm)o;
        String password = userForm.getPassword();

        int upperCase = 0;
        int lowerCase = 0;

        for (int k = 0; k < password.length(); k++) {
            if (Character.isUpperCase(password.charAt(k))) upperCase++;
            if (Character.isLowerCase(password.charAt(k))) lowerCase++;
        }

        if(upperCase == 0) {
            errors.rejectValue("password", "password.validator.uppercase");
        }
        if(lowerCase == 0) {
            errors.rejectValue("password", "password.validator.lowercase");
        }
    }
}
