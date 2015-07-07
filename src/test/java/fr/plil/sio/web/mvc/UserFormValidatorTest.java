package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserFormValidatorTest {

    private UserForm user;
    private UserFormValidator validator;
    private Errors results;

    @Before
    public void createInstances() {
        validator = new UserFormValidator();
        user = new UserForm();
        user.setUsername("abc");
        user.setPassword("abc#A");
        results = new BeanPropertyBindingResult(user, "user");
    }

    @Test
    public void testSupports() {
        assertTrue(validator.supports(UserForm.class));
    }

    @Test
    public void testValidateCorrect() {
        validator.validate(user,results);
        assertFalse(results.hasErrors());
    }

}
