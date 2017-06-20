package fr.plil.sio.web.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserRestController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/api/users/", method = RequestMethod.GET)
    public List<User> listUsers() {
        return userService.findAll();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {}

    @RequestMapping(value = "/api/users/{username}/", method = RequestMethod.GET)
    public User listUsers(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new ResourceNotFoundException();
        } else {
            return user;
        }
    }
}