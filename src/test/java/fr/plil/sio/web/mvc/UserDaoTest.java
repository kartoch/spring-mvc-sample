package fr.plil.sio.web.mvc;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void createInstances() {
        userDao = new UserDaoImpl();
    }

    @Test
    public void testAdminPresent() {
        assertEquals(1, userDao.getAllUsers().size());
        assertNotNull(userDao.getFromUsername("admin"));
        assertEquals("admin", userDao.getFromUsername("admin").getUsername());
    }

    @Test
    public void testSaveSucceed() {
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        assertTrue(userDao.save(user));
    }

    @Test
    public void testSaveFailed() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("abc");
        assertFalse(userDao.save(user));
    }

    @Test
    public void testGetFromUsernameFound() {
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        userDao.save(user);
        assertNotNull(userDao.getFromUsername("admin"));
        assertEquals("admin",userDao.getFromUsername("admin").getUsername());
    }

    @Test
    public void testGetFromUsernameNotFound() {
        assertNull(userDao.getFromUsername("abc"));
    }

    @Test
    public void testGetAllUsers() {
        assertEquals(1,userDao.getAllUsers().size());
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        assertTrue(userDao.save(user));
        assertEquals(2,userDao.getAllUsers().size());
    }
}