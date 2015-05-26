package fr.plil.sio.web.mvc;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void createInstances() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    public void testAdminPresent() {
        assertEquals(1, userRepository.getAllUsers().size());
        assertNotNull(userRepository.getFromUsername("admin"));
        assertEquals("admin", userRepository.getFromUsername("admin").getUsername());
    }

    @Test
    public void testSaveSucceed() {
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        assertTrue(userRepository.save(user));
    }

    @Test
    public void testSaveFailed() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("abc");
        assertFalse(userRepository.save(user));
    }

    @Test
    public void testGetFromUsernameFound() {
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        userRepository.save(user);
        assertNotNull(userRepository.getFromUsername("admin"));
        assertEquals("admin", userRepository.getFromUsername("admin").getUsername());
    }

    @Test
    public void testGetFromUsernameNotFound() {
        assertNull(userRepository.getFromUsername("abc"));
    }

    @Test
    public void testGetAllUsers() {
        assertEquals(1, userRepository.getAllUsers().size());
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        assertTrue(userRepository.save(user));
        assertEquals(2, userRepository.getAllUsers().size());
    }
}