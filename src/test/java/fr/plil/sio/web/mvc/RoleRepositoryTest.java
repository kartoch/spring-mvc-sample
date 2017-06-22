package fr.plil.sio.web.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testDefaultRolesPresent() {
        assertEquals(2, roleRepository.findAll().size());
        assertNotNull(roleRepository.findByName("ROLE_ADMIN"));
        assertNotNull(roleRepository.findByName("ROLE_USER"));
    }
}