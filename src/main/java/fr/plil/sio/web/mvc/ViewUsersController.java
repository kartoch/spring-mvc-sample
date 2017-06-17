package fr.plil.sio.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ViewUsersController {

    @Autowired
    private SecurityService securityService;

    @Resource
    private UserRepository userRepository;

    @ModelAttribute("users")
    public List<User> populateUsers() {
        return userRepository.findAll();
    }

    @ModelAttribute("connectedUser")
    public String populateConnectedUser() { return securityService.findLoggedInUsername(); }

    @RequestMapping(value={"/"},method=RequestMethod.GET)
    public String getViewUsers() {
        return "viewUsers";
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
