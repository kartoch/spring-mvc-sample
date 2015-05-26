package fr.plil.sio.web.mvc;

import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewUsersController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserSession userSession;

    @ModelAttribute("users")
    public Set<User> populateUsers() {
        return userRepository.getAllUsers();
    }

    @ModelAttribute("userSession")
    public UserSession populateUser() {
        return userSession;
    }

    @RequestMapping(value={"/"},method=RequestMethod.GET)
    public String getViewUsers() {
        return "viewUsers";
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
