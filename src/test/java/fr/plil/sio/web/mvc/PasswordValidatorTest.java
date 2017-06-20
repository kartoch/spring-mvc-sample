package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    private BindingResult results;

    private UserForm userForm;

    @Before
    public void before() {
        passwordValidator = new PasswordValidator();
        userForm = new UserForm();
        userForm.setUsername("login");
        results = new BeanPropertyBindingResult(userForm, "userForm");
    }

    @Test
    public void testValidate() {
        userForm.setPassword("RaNdOM");
        passwordValidator.validate(userForm, results);
        assertEquals(0, results.getErrorCount());
    }


    @Test
    public void testValidateNoUppercase() {
        userForm.setPassword("lowercase");
        passwordValidator.validate(userForm, results);
        assertEquals(1, results.getErrorCount());
    }

    @Test
    public void testValidateNoLowercase() {
        userForm.setPassword("UPPERCASE");
        passwordValidator.validate(userForm, results);
        assertEquals(1, results.getErrorCount());
    }
}
