package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecurityServiceTest {

    private SecurityService securityService;

    private SecurityContext securityContext;

    @Before
    public void before() {
        securityService = new SecurityServiceImpl();
        securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test(expected = IllegalStateException.class)
    public void testFindLoggedInUsernameFailedWhenNullAuthentication() {
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);
        securityService.findLoggedInUsername();
    }

    @Test(expected = IllegalStateException.class)
    public void testFindLoggedInUsernameFailedWhenPrincipalNull() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(null);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        securityService.findLoggedInUsername();
    }

    @Test(expected = IllegalStateException.class)
    public void testFindLoggedInUsernameFailedWhenPrincipalNotUserDetails() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(new Object());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        securityService.findLoggedInUsername();
    }

    @Test
    public void testFindLoggedInUsername() {
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("admin");
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        assertEquals("admin",securityService.findLoggedInUsername());
    }
}
