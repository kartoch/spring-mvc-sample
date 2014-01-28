package fr.plil.sio.web.mvc;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ViewUsersControllerTest {

    private ViewUsersController viewUsersController;
    private UserDao userDao;
    private UserSession userSession;

    @Before
    public void createInstances() {
        viewUsersController = new ViewUsersController();
        userDao = new UserDaoImpl();
        viewUsersController.setUserDao(userDao);
        userSession = new UserSession();
        userSession.setUsername("admin");
        viewUsersController.setUserSession(userSession);
    }

    @Test
    public void testPopulateUsers() {
        assertEquals(1, viewUsersController.populateUsers().size());
    }

    @Test
    public void testPopulateUser() {
        assertEquals("admin", viewUsersController.populateUser().getUsername());
    }

    @Test
    public void testGetViewUsers() {
        assertEquals("viewUsers",viewUsersController.getViewUsers());
    }
}
