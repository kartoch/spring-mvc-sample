package fr.plil.sio.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class NewUserController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserSession userSession;

    @Resource
    private UserFormValidator userFormValidator;

    @RequestMapping(value = {"/newUser"}, method = RequestMethod.GET)
    public ModelAndView getNewUserForm() {
        return new ModelAndView("newUser", "user", new User());
    }

    @RequestMapping(value = {"/newUser"}, method = RequestMethod.POST)
    public String postNewUser(User user, BindingResult result) {

        if (!userSession.getUsername().equals("admin")) {
            result.rejectValue("username", "new.user.only.admin");
        }

        userFormValidator.validate(user, result);

        boolean present = (userRepository.findByUsername(user.getUsername()) != null);

        if (present) {
            result.rejectValue("username", "new.user.form.present");
        }

        if(result.hasErrors()) {
            return "newUser";
        }

        userRepository.save(user);

        return "redirect:/";
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public void setUserFormValidator(UserFormValidator userFormValidator) {
        this.userFormValidator = userFormValidator;
    }
}
