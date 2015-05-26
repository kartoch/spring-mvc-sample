package fr.plil.sio.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Resource
    private UserRepository userRepository;
    
    @Resource
    private UserSession userSession;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginForm() {
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postLoginCheck(User user, BindingResult result) {

        User userFromDao = userRepository.findByUsername(user.getUsername());

        if (userFromDao == null) {
            result.rejectValue("username","login.form.invalid");
            return "login";
        }

        if(!userFromDao.getPassword().equals(user.getPassword())) {
            result.rejectValue("username","login.form.invalid");
            return "login";            
        }
        
        userSession.setUsername(userFromDao.getUsername());

        return "redirect:/";
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}