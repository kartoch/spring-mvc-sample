package fr.plil.sio.web.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserRestController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public List<User> listUsers() {
        return userService.findAll();
    }
}