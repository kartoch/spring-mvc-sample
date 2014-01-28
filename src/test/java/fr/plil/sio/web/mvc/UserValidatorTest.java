package fr.plil.sio.web.mvc;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class UserValidatorTest {

    private User user;
    private UserValidator validator;
    private Errors results;

    @Before
    public void createInstances() {
        validator = new UserValidator();
        user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        results = new BeanPropertyBindingResult(user, "user");
    }

    @Test
    public void testSupports() {
        assertTrue(validator.supports(User.class));
    }

    @Test
    public void testValidateCorrect() {
        validator.validate(user,results);
        assertFalse(results.hasErrors());
    }

    @Test
    public void testValidatePasswordTooShort() {
        user.setPassword("ab");
        validator.validate(user,results);
        assertTrue(results.hasErrors());
    }

    @Test
    public void testValidateUsernameTooShort() {
        user.setUsername("ab");
        validator.validate(user,results);
        assertTrue(results.hasErrors());
    }
}
