package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NewUserControllerTest {

    private NewUserController newUserController;
    private BindingResult results;
    private UserForm user;
    private UserRepository userRepository;
    private UserSession userSession;
    private UserFormValidator userFormValidator;
    private UserService userService;

    @Before
    public void createInstances() {
        newUserController = new NewUserController();
        user = new UserForm();
        results = new BeanPropertyBindingResult(user, "user");
        userRepository = mock(UserRepository.class);
        User user = new User("admin", "password");
        when(userRepository.findByUsername("admin")).thenReturn(user);
        newUserController.setUserRepository(userRepository);
        userService = mock(UserService.class);
        newUserController.setUserService(userService);
        userSession = new UserSession();
        userSession.setUsername("admin");
        newUserController.setUserSession(userSession);
        userFormValidator = new UserFormValidator();
        newUserController.setUserFormValidator(userFormValidator);
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
        user.setPassword("abcD#");
        String view = newUserController.postNewUser(user, results);
        assertFalse(results.hasErrors());
        assertEquals("redirect:/", view);
        verify(userService).createUser("abc", "abcD#");
    }

    @Test
    public void testPostNewUserFailedNotAdmin() {
        user.setUsername("abc");
        user.setPassword("abcD#");
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
