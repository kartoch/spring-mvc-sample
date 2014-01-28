package fr.plil.sio.web.mvc;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class CheckUserInterceptorTest {

    private CheckUserInterceptor interceptor;
    private UserSession userSession;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void createInstances() {
        interceptor = new CheckUserInterceptor();
        userSession = new UserSession();
        interceptor.setUserSession(userSession);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void checkPreHandleServletPathIsLogin() throws IOException {
        request.setPathInfo("/login");
        assertTrue(interceptor.preHandle(request, response, null));
    }

    @Test
    public void checkPreHandleUsernameInSession() throws IOException {
        userSession.setUsername("admin");
        request.setPathInfo("/blabla");
        assertTrue(interceptor.preHandle(request, response, null));
    }

    @Test
    public void checkPreHandleUsernameNotInSession() throws IOException {
        request.setPathInfo("/blabla");
        assertFalse(interceptor.preHandle(request, response, null));
        assertEquals(response.getRedirectedUrl(),"login");
    }
}
