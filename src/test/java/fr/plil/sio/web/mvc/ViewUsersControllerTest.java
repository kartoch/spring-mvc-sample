package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewUsersControllerTest {

    private ViewUsersController viewUsersController;
    private UserRepository userRepository;
    private UserSession userSession;

    @Before
    public void createInstances() {
        viewUsersController = new ViewUsersController();
        userRepository = mock(UserRepository.class);
        List<User> users = new LinkedList<>();
        users.add(new User("admin", "password"));
        when(userRepository.findAll()).thenReturn(users);
        viewUsersController.setUserRepository(userRepository);
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
