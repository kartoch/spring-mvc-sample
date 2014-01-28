package fr.plil.sio.web.mvc;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

    @Resource
    private UserSession userSession;

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String getLogout() {
        userSession.setUsername(null);
        return "redirect:/";
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
