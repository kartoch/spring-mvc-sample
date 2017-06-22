package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ViewUsersControllerTest {

    private ViewUsersController viewUsersController;

    private UserRepository userRepository;

    private SecurityService securityService;

    private MockMvc mockMvc;

    @Before
    public void createInstances() {
        viewUsersController = new ViewUsersController();
        mockMvc = MockMvcBuilders.standaloneSetup(viewUsersController).build();
        userRepository = mock(UserRepository.class);
        List<User> users = new LinkedList<>();
        User testUser = new User();
        testUser.setPassword("admin");
        testUser.setUsername("password");
        users.add(testUser);
        when(userRepository.findAll()).thenReturn(users);
        viewUsersController.setUserRepository(userRepository);
        securityService = mock(SecurityService.class);
        when(securityService.findLoggedInUsername()).thenReturn("admin");
        viewUsersController.setSecurityService(securityService);
    }

    @Test
    public void testPopulateUsers() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewUsers"))
                .andExpect(model().attributeExists("users"));
    }
}
