package fr.plil.sio.web.mvc;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class LoginControllerTest {

    private LoginController loginController;
    private BindingResult results;
    private User user;
    private UserDao userDao;
    private UserSession userSession;

    @Before
    public void createInstances() {
        loginController = new LoginController();
        user = new User();
        results = new BeanPropertyBindingResult(user, "user");
        userDao = new UserDaoImpl();
        loginController.setUserDao(userDao);
        userSession = new UserSession();
        loginController.setUserSession(userSession);
    }

    @Test
    public void testGetLoginForm() {
        ModelAndView mav = loginController.getLoginForm();
        assertEquals("login", mav.getViewName());
        assertEquals(1, mav.getModelMap().size());
        assertTrue(mav.getModel().containsKey("user"));
        assertTrue(mav.getModel().get("user") instanceof User);
    }

    @Test
    public void testPostLoginCheckSucceed() {
        user.setUsername("admin");
        user.setPassword("admin");
        String view = loginController.postLoginCheck(user, results);
        assertFalse(results.hasErrors());
        assertEquals("redirect:/",view);
    }

    @Test
    public void testPostLoginCheckFailed() {
        user.setUsername("abc");
        user.setPassword("abc");
        String view = loginController.postLoginCheck(user, results);
        assertTrue(results.hasErrors());
        assertEquals("login",view);
    }
}
