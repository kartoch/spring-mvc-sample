package fr.plil.sio.web.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ViewUsersControllerTest {

    private ViewUsersController viewUsersController;
    private UserRepository userRepository;
    private UserSession userSession;

    private MockMvc mockMvc;

    @Before
    public void createInstances() {
        viewUsersController = new ViewUsersController();
        mockMvc = MockMvcBuilders.standaloneSetup(viewUsersController).build();
        userRepository = mock(UserRepository.class);
        List<User> users = new LinkedList<>();
        users.add(new User("admin", "password"));
        when(userRepository.findAll()).thenReturn(users);
        viewUsersController.setUserRepository(userRepository);
        userSession = new UserSession();
        userSession.setUsername("admin");
        viewUsersController.setUserSession(userSession);
    }

    @Test
    public void testPopulateUsers() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewUsers"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("userSession"));
    }
}
