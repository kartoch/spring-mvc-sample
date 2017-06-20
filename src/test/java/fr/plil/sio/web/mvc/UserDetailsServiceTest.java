package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailsServiceTest {

    private UserDetailsService userDetailsService;

    private UserService userService;

    @Before
    public void before() {
        userDetailsService = new UserDetailsServiceImpl();
        userService = mock(UserService.class);
        when(userService.findByUsername("working")).thenReturn(new User());
        ((UserDetailsServiceImpl)userDetailsService).setUserService(userService);
    }

    @Test
    public void testLoadUserByUsername() {
        assertNotNull(userDetailsService.loadUserByUsername("working"));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByNotExistingUsername() {
        userDetailsService.loadUserByUsername("not-working");
    }
}
