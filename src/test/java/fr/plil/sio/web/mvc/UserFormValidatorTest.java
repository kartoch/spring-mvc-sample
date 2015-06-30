package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserFormValidatorTest {

    private User user;
    private UserFormValidator validator;
    private Errors results;

    @Before
    public void createInstances() {
        validator = new UserFormValidator();
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
