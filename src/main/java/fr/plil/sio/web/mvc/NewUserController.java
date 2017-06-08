package fr.plil.sio.web.mvc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/newUser"})
public class NewUserController {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getNewUserForm() {
        return new ModelAndView("newUser", "userForm", new UserForm());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public String postNewUser(@Valid UserForm userForm, BindingResult result) {

        boolean present = (userService.findByUsername(userForm.getUsername()) != null);

        if (present) {
            result.rejectValue("username", "new.user.form.present");
        }

        if(result.hasErrors()) {
            return "newUser";
        }

        userService.createUser(userForm.getUsername(), userForm.getPassword());

        return "redirect:/";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}