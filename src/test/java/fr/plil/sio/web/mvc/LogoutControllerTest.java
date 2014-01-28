package fr.plil.sio.web.mvc;

import static org.junit.Assert.*;
import org.junit.Test;

public class LogoutControllerTest {

    @Test
    public void testGetLogout() {
        LogoutController logoutController = new LogoutController();
        UserSession userSession = new UserSession();
        userSession.setUsername("blabla");
        logoutController.setUserSession(userSession);
        String view = logoutController.getLogout();
        assertNull(userSession.getUsername());
        assertEquals("redirect:/",view);
    }
}
