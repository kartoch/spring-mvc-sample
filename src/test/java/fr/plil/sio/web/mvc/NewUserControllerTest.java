package fr.plil.sio.web.mvc;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class NewUserControllerTest {

    private NewUserController newUserController;
    private BindingResult results;
    private User user;
    private UserDao userDao;
    private UserSession userSession;
    private UserValidator userValidator;

    @Before
    public void createInstances() {
        newUserController = new NewUserController();
        user = new User();
        results = new BeanPropertyBindingResult(user, "user");
        userDao = new UserDaoImpl();
        newUserController.setUserDao(userDao);
        userSession = new UserSession();
        userSession.setUsername("admin");
        newUserController.setUserSession(userSession);
        userValidator = new UserValidator();
        newUserController.setUserValidator(userValidator);
    }

    @Test
    public void testGetNewUserForm() {
        ModelAndView mav = newUserController.getNewUserForm();
        assertEquals("newUser", mav.getViewName());
        assertEquals(1, mav.getModelMap().size());
        assertTrue(mav.getModel().containsKey("user"));
        assertTrue(mav.getModel().get("user") instanceof User);
    }

    @Test
    public void testPostNewUserSucceed() {
        user.setUsername("abc");
        user.setPassword("abc");
        String view = newUserController.postNewUser(user, results);
        assertFalse(results.hasErrors());
        assertEquals("redirect:/",view);
    }

    @Test
    public void testPostNewUserFailedNotAdmin() {
        user.setUsername("abc");
        user.setPassword("abc");
        userSession.setUsername("blabla");
        String view = newUserController.postNewUser(user, results);
        assertTrue(results.hasErrors());
        assertEquals("newUser",view);
    }

    @Test
    public void testPostNewUserFailedValidate() {
        user.setUsername("a");
        user.setPassword("abc");
        String view = newUserController.postNewUser(user, results);
        assertTrue(results.hasErrors());
        assertEquals("newUser",view);
    }

    @Test
    public void testPostNewUserFailedAlreadyPresent() {
        user.setUsername("admin");
        user.setPassword("blabla");
        String view = newUserController.postNewUser(user, results);
        assertTrue(results.hasErrors());
        assertEquals("newUser",view);
    }
}
