package fr.plil.sio.web.mvc;

import java.io.IOException;import javax.annotation.Resource;
;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CheckUserInterceptor.class);
    
    @Resource
    private UserSession userSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws IOException {

        logger.debug("path info: " + request.getPathInfo());
                            
        if (request.getPathInfo().equals("/login")) {
            logger.debug("access granted as path is /login");                            
            return true;
        }

        String username = userSession.getUsername();

        if (username != null) {
            logger.debug("authenticated");                                        
            return true;
        } else {
            logger.debug("not authenticated");                                                    
            response.sendRedirect("login");
            return false;
        }
    }

    void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
