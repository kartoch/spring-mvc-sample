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
    private UserForm userForm;
    private UserService userService;

    @Before
    public void createInstances() {
        newUserController = new NewUserController();
        userForm = new UserForm();
        results = new BeanPropertyBindingResult(userForm, "user");
        userService = mock(UserService.class);
        User user = new User();
        when(userService.findByUsername("admin")).thenReturn(user);
        newUserController.setUserService(userService);
        userService = mock(UserService.class);
        newUserController.setUserService(userService);
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
        userForm.setUsername("abc");
        userForm.setPassword("abcD#");
        String view = newUserController.postNewUser(userForm, results);
        assertFalse(results.hasErrors());
        assertEquals("redirect:/", view);
        verify(userService).createUser("abc", "abcD#");
    }

    @Test
    public void testPostNewUserFailedNotAdmin() {
        userForm.setUsername("abc");
        userForm.setPassword("abcD#");
        String view = newUserController.postNewUser(userForm, results);
        assertTrue(results.hasErrors());
        assertEquals("newUser",view);
    }

    @Test
    public void testPostNewUserFailedValidate() {
        userForm.setUsername("a");
        userForm.setPassword("abc");
        String view = newUserController.postNewUser(userForm, results);
        assertTrue(results.hasErrors());
        assertEquals("newUser",view);
    }

    @Test
    public void testPostNewUserFailedAlreadyPresent() {
        userForm.setUsername("admin");
        userForm.setPassword("blabla");
        String view = newUserController.postNewUser(userForm, results);
        assertTrue(results.hasErrors());
        assertEquals("newUser",view);
    }
}
