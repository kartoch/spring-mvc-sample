package fr.plil.sio.web.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Resource
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        userService.createUser("blabla", "polo");
        assertEquals("blabla", userRepository.findByUsername("blabla").getUsername());
    }
}
