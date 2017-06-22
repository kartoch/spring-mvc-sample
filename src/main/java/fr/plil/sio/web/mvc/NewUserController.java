package fr.plil.sio.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/* This controller deals with GET and POST methods for URL '/newUser' */
@Controller
@RequestMapping(value = {"/newUser"})
public class NewUserController {

    @Autowired
    private UserService userService;

    /*
     * To have dual validations (by PasswordValidator AND JSR-303 annotations in UserForm), it is needed to add
     * explicitly the Validators
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PasswordValidator());
    }

    /*
     * GET request returns the form with an empty UserForm for POST request data binding
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getNewUserForm() {
        return new ModelAndView("newUser", "userForm", new UserForm());
    }

    /*
     * POST request populate userForm with form data and create the user with the requested username and password.
     * This method can be called only by user with role ROLE_ADMIN.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public String postNewUser(@Valid UserForm userForm, BindingResult result) {

        // search the user found in userForm
        boolean present = (userService.findByUsername(userForm.getUsername()) != null);

        // if not present, add error message to result
        if (present) {
            result.rejectValue("username", "new.user.form.present");
        }

        // if result contains validation errors, return the form (spring passes the validation errors to the form).
        if(result.hasErrors()) {
            return "newUser";
        }

        // create the user
        userService.createUser(userForm.getUsername(), userForm.getPassword());

        // redirect the client using 302 HTTP code
        return "redirect:/";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}