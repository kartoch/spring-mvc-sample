package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginControllerTest {

    private LoginController controller;

    @Before
    public void before() {
        controller = new LoginController();
    }

    @Test
    public void testLogin() {
        assertFalse(controller.login(null,null).getModel().containsKey("message"));
    }

    @Test
    public void testLoginWithError() {
        assertTrue(controller.login("error",null).getModel().containsKey("message"));
    }

    @Test
    public void testLoginWithLogout() {
        assertTrue(controller.login(null,"logout").getModel().containsKey("message"));    }
}
