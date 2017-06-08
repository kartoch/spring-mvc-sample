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
        user.setUsername("admin");
        user.setUsername("blabla");
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
        assertTrue(mav.getModel().containsKey("userForm"));
        assertTrue(mav.getModel().get("userForm") instanceof UserForm);
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
}
