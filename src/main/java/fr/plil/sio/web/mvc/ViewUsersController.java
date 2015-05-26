package fr.plil.sio.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ViewUsersController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserSession userSession;

    @ModelAttribute("users")
    public List<User> populateUsers() {
        return userRepository.findAll();
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
