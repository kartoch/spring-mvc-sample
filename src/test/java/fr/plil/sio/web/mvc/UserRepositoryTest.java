package fr.plil.sio.web.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testAdminPresent() {
        assertEquals(1, userRepository.findAll().size());
        assertNotNull(userRepository.findByUsername("admin"));
        assertEquals("admin", userRepository.findByUsername("admin").getUsername());
    }
}